package io.hndrs.slack.broker.installation

import com.slack.api.Slack
import io.hndrs.slack.broker.store.team.Team
import io.hndrs.slack.broker.store.team.TeamStore
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.servlet.view.RedirectView

/**
 * InstallationBroker provides an endpoint that is responsible for installation requests.
 *
 * It will execute all registered [InstallationHandler]s on an installation request
 * If **all** [InstallationHandler]s * execute successfully (return without exception)
 * will redirect to a configurable successRedirectUrl. If any of the registered
 * [InstallationHandler]s throws any exception it will redirect to another configurable errorRedirectUrl
 *
 * Notes:
 * At this point there is no guarantee on the execution order of the [InstallationHandler]s
 */
@SuppressWarnings("detekt:TooGenericExceptionCaught")
@RestController
class InstallationEndpoint constructor(
    private val installationHandlers: List<InstallationHandler>,
    private val teamStore: TeamStore,
    private val config: Config,
    private val slack: Slack,
) {
    /**
     * Installation-endpoint which is called by slack
     * Obtains the token by calling [oauth.access](https://api.slack.com/methods/oauth.access)
     * and saves the response into the TeamStore
     */
    @Suppress("UnusedPrivateMember")
    @GetMapping(value = ["/installation"])
    fun onInstall(@RequestParam("code") code: String, @RequestParam("state") state: String): RedirectView {
        return try {
            val team = obtainOauthAccess(code)
            this.teamStore.put(team)

            this.installationHandlers
                .forEach { receiver ->
                    try {
                        receiver.onInstallation(team)
                    } catch (e: Exception) {
                        LOG.error("Execution of {} failed", receiver::class.simpleName, e)
                    }
                }
            RedirectView(this.config.successRedirectUrl)
        } catch (exception: Exception) {
            LOG.error("There was an error during the installation", exception)
            RedirectView(this.config.errorRedirectUrl)
        }
    }

    private fun obtainOauthAccess(code: String): Team {
        return this.slack.methods().oauthV2Access {
            it
                .clientId(config.clientId)
                .clientSecret(config.clientSecret)
                .code(code)
        }.let {
            if (it.isOk) {
                Team(
                    teamId = it.team.id,
                    teamName = it.team.name,
                    accessToken = it.accessToken
                )
            } else {
                throw ResponseStatusException(HttpStatus.UNAUTHORIZED, it.toString())
            }
        }
    }

    /**
     * Configuration that is needed to install the app
     */
    data class Config(
        val clientId: String,
        val clientSecret: String,
        val successRedirectUrl: String,
        val errorRedirectUrl: String,
    )

    companion object {
        private val LOG = LoggerFactory.getLogger(InstallationEndpoint::class.java)
    }
}
