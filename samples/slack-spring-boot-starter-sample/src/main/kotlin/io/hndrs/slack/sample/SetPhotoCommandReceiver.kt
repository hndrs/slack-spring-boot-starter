package io.hndrs.slack.sample

import io.hndrs.slack.api.SlackClient
import io.hndrs.slack.api.contract.jackson.SlackCommand
import io.hndrs.slack.api.contract.jackson.group.users.SetPhotoRequest
import io.hndrs.slack.broker.receiver.SlashCommandReceiver
import io.hndrs.slack.broker.store.team.Team
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import java.io.File

@Component
class SetPhotoCommandReceiver @Autowired constructor(private val slackClient: io.hndrs.slack.api.SlackClient) : SlashCommandReceiver {
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
