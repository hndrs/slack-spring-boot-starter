package io.hndrs.slack.broker.store.user

import com.slack.api.Slack
import io.hndrs.slack.broker.installation.InstallationReceiver
import io.hndrs.slack.broker.store.team.Team
import io.hndrs.slack.broker.util.on
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired

/**
 * Receiver that stores all users after a team installs the app
 */
class UserInstallationReceiver @Autowired constructor(
    private val slack: Slack,
    private val userStore: UserStore,
) : InstallationReceiver {

    @Suppress("SpreadOperator")
    override fun onInstallation(team: Team) {
        Slack.getInstance()
        slack.methods().usersList {
            it.limit(BATCH_SIZE).includeLocale(true)
        }.on(
            { this.userStore.put(*it.members.map { userOfMember(it) }.toTypedArray()) },
            { LOG.error("Failure while trying to load users\n{}", it) }
        )
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(UserInstallationReceiver::class.java)
        private const val BATCH_SIZE = 100
    }
}
