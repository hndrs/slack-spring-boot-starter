package com.kreait.slack.sample

import com.kreait.slack.api.SlackClient
import com.kreait.slack.api.contract.jackson.SlackCommand
import com.kreait.slack.api.contract.jackson.group.users.SetPhotoRequest
import com.kreait.slack.broker.receiver.SlashCommandReceiver
import com.kreait.slack.broker.store.team.Team
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import java.io.File

@Component
class SetPhotoCommandReceiver @Autowired constructor(private val slackClient: SlackClient) : SlashCommandReceiver {
    override fun supportsCommand(slackCommand: SlackCommand): Boolean {
        return slackCommand.command.startsWith("/ping")
    }

    override fun onReceiveSlashCommand(slackCommand: SlackCommand, headers: HttpHeaders, team: Team) {
        this.slackClient.users()
                .setPhoto("YOUR_TOKEN")
                .with(SetPhotoRequest(File(SetPhotoCommandReceiver::class.java.classLoader.getResource("olaph.png").file)))
                .onSuccess {
                    println(it)
                }.onFailure {
                    println(it)
                }.invoke()

    }
}
