package io.olaph.slack.broker.broker

import io.olaph.slack.broker.receiver.InstallationReceiver
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.view.RedirectView

/**
 * InstallationBroker provides an endpoint that is responsible for installation requests.
 *
 * It will execute all registered [InstallationReceiver]s on an installation request
 * If **all** [InstallationReceiver]s * execute successfully (return without exception) will redirect to a configurable successRedirectUrl.
 * If any of the registered [InstallationReceiver]s throws any exception it will redirect to another configurable errorRedirectUrl
 *
 * Notes:
 * At this point there is no guarantee on the execution order of the [InstallationReceiver]s
 */
@RestController
class InstallationBroker constructor(
        private val successRedirectUrl: String,
        private val errorRedirectUrl: String,
        private val installationReceivers: List<InstallationReceiver>
) {

    companion object {
        private val LOG = LoggerFactory.getLogger(InstallationBroker::class.java)
    }

    @GetMapping(value = ["/installation"])
    fun onInstall(@RequestParam("code") code: String, @RequestParam("state") state: String): RedirectView {
        return try {

            this.installationReceivers
                    .forEach { receiver ->
                        receiver.onReceiveInstallation(code, state)
                    }

            RedirectView(successRedirectUrl)
        } catch (exception: Exception) {
            LOG.error("There was an error during the installation", exception)
            RedirectView(errorRedirectUrl)
        }

    }
}
