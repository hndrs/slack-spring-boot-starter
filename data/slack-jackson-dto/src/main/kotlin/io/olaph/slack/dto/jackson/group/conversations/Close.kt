package io.olaph.slack.dto.jackson.group.conversations

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.olaph.slack.dto.jackson.JacksonDataClass

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulConversationCloseResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorConversationCloseResponse::class, name = "false")
)

@JacksonDataClass
sealed class SlackConversationCloseResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * [SlackDoc](https://api.slack.com/methods/conversations.close)
 */
data class SuccessfulConversationCloseResponse(
        override val ok: Boolean,
        @JsonProperty("no_op") val noOp: Boolean?,
        @JsonProperty("already_closed") val alreadyClosed: Boolean?
) : SlackConversationCloseResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/conversations.close)
 */
data class ErrorConversationCloseResponse constructor(
        override val ok: Boolean,
        @JsonProperty("error") val error: String
) : SlackConversationCloseResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/conversations.close)
 */
data class ConversationCloseRequest(@JsonProperty("channel") val channel: String) {
    companion object
}
