package io.hndrs.slack.sample.rock_paper_scissors

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import io.hndrs.slack.api.SlackClient
import io.hndrs.slack.api.contract.jackson.SlackCommand
import io.hndrs.slack.api.contract.jackson.common.messaging.Block
import io.hndrs.slack.api.contract.jackson.common.messaging.Element
import io.hndrs.slack.api.contract.jackson.common.messaging.composition.Option
import io.hndrs.slack.api.contract.jackson.common.messaging.composition.Text
import io.hndrs.slack.api.contract.jackson.group.respond.RespondMessageRequest
import io.hndrs.slack.api.contract.jackson.group.respond.ResponseType
import io.hndrs.slack.broker.receiver.SlashCommandReceiver
import io.hndrs.slack.broker.store.team.Team
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.stereotype.Service


@Service
class BlockTests @Autowired constructor(private val slackClient: io.hndrs.slack.api.SlackClient) : SlashCommandReceiver {

    override fun supportsCommand(slackCommand: SlackCommand): Boolean {
        return slackCommand.command.startsWith("/blocks")
    }

    override fun onReceiveSlashCommand(slackCommand: SlackCommand, headers: HttpHeaders, team: Team) {
        println(slackCommand.responseUrl)
        this.slackClient.respond().message(slackCommand.responseUrl)
            .with(assembleBlockRequest())
            .onSuccess { println(it) }
            .onFailure { println(it) }
            .invoke()
    }

    private fun assembleBlockRequest(): RespondMessageRequest {
        val section = Block.Section(
            Text(Text.Type.PLAIN_TEXT, "this is a section"),
            fields = listOf(
                Text(Text.Type.PLAIN_TEXT, "it can contain "),
                Text(Text.Type.PLAIN_TEXT, "many text-fields")
            ), blockId = "section"
        )
        val divider = Block.Divider()
        val image = Block.Image(IMAGE_URL, ALT_TEXT, blockId = "image")
        val actions = Block.Action(assembleElements(), blockId = "Action")
        val context = Block.Context(
            blockId = "context", elements = listOf(
                Element.Image(imageUrl = IMAGE_URL, altText = ALT_TEXT),
                Text(Text.Type.PLAIN_TEXT, "Logo")
            )
        )

        val request = RespondMessageRequest(
            responseType = ResponseType.EPHEMERAL, text = "test",
            blocks = listOf(section, divider, image, actions, context)
        )
        println(
            Jackson2ObjectMapperBuilder
                .json()
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .failOnUnknownProperties(false)
                .build<ObjectMapper>().writeValueAsString(request)
        )

        return request
    }

    private fun assembleElements(): List<Element> {
        return listOf(
            Element.Button(text = Text(Text.Type.PLAIN_TEXT, "This is a Button"), actionId = "button123"),
            Element.UsersSelect(
                actionId = "user_select",
                placeholderText = Text(Text.Type.PLAIN_TEXT, "Select a User")
            ),
            Element.StaticSelect(
                actionId = "static_select", placeholderText = Text(Text.Type.PLAIN_TEXT, "select something"),
                options = listOf(
                    Option(Text(Text.Type.PLAIN_TEXT, "first entry"), value = "first_entry"),
                    Option(Text(Text.Type.PLAIN_TEXT, "second entry"), value = "second_entry"),
                    Option(Text(Text.Type.PLAIN_TEXT, "third entry"), value = "third_entry")
                )
            ),
            Element.ConversationsSelect(
                actionId = "convo_select",
                placeholderText = Text(Text.Type.PLAIN_TEXT, "select a conversation")
            ),
            Element.ChannelsSelect(
                actionId = "channel_select",
                placeholderText = Text(Text.Type.PLAIN_TEXT, "select a channel")
            ),
            Element.DatePicker(actionId = "date_picker"),
            Element.Overflow(
                actionId = "overflow",
                options = listOf(
                    Option(Text(Text.Type.PLAIN_TEXT, "option1"), "option1"),
                    Option(Text(Text.Type.PLAIN_TEXT, "option2"), "option2"),
                    Option(Text(Text.Type.PLAIN_TEXT, "option3"), "option3")
                )
            )
        )

    }

    companion object{

        private const val ALT_TEXT = "Logo"
        private const val IMAGE_URL =
            "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png"
    }
}
