package com.kreait.slack.sample

import com.kreait.slack.api.SlackClient
import com.kreait.slack.api.contract.jackson.SlackCommand
import com.kreait.slack.api.contract.jackson.group.chat.ParseType.FULL
import com.kreait.slack.api.contract.jackson.group.chat.PostMessageRequest
import com.kreait.slack.api.contract.jackson.group.users.SetPhotoRequest
import com.kreait.slack.broker.receiver.SlashCommandReceiver
import com.kreait.slack.broker.store.Team
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component

@Component
class PingCommandReceiver @Autowired constructor(private val slackClient: SlackClient) : SlashCommandReceiver {
    override fun supportsCommand(slackCommand: SlackCommand): Boolean {
        return slackCommand.command.startsWith("/ping")
    }

    override fun onReceiveSlashCommand(slackCommand: SlackCommand, headers: HttpHeaders, team: Team) {
        this.slackClient.chat().postMessage(team.bot.accessToken)
                .with(PostMessageRequest(
                        text = "Pong",
                        channel = slackCommand.channelId,
                        parse = FULL
                )).onSuccess {
                    this.slackClient.users()
                            .setPhoto("xoxp-354639396245-370485571863-483316385537-230fa6a5872b0c698a4b2de17facfec8")
                            .with(SetPhotoRequest(javaClass.getResourceAsStream("/smiley.png"), 100, 0, 0))
                            .onSuccess {
                                println(it)
                            }.onFailure {
                                println(it)
                            }.invoke()

                }.onFailure {
                    println(it)
                }
                .invoke()
    }
}
