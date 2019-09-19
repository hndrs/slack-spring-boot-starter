package com.kreait.slack.broker.store.user

import com.kreait.slack.api.SlackClient
import com.kreait.slack.api.contract.jackson.common.types.Member
import com.kreait.slack.api.contract.jackson.group.users.ListAllRequest
import com.kreait.slack.broker.receiver.InstallationReceiver
import com.kreait.slack.broker.store.team.Team
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired

class UserInstallationReceiver @Autowired constructor(private val slackClient: SlackClient,
                                                      private val userStore: UserStore) : InstallationReceiver {


    override fun onReceiveInstallation(code: String, state: String, team: Team) {
        this.slackClient.users().listAll(authToken = team.bot.accessToken)
                .with(ListAllRequest(true, 100))
                .onSuccess { response ->
                    this.userStore.put(*response.members.map { userOfMember(it) }.toTypedArray())
                    if (LOG.isDebugEnabled)
                        LOG.debug("successfully downloaded users")
                }
                .onFailure {
                    LOG.error("failure while trying to load users")
                }
                .invoke()
    }

    companion object {
        val LOG = LoggerFactory.getLogger(UserInstallationReceiver::class.java)

    }
}