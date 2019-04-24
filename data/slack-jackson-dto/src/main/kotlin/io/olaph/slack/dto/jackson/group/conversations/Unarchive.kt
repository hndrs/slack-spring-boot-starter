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
        JsonSubTypes.Type(value = SuccessfulConversationUnarchiveResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorConversationUnarchiveResponse::class, name = "false")
)
@JacksonDataClass
sealed class ConversationUnarchiveResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * [SlackDoc](https://api.slack.com/methods/conversations.archive)
 */
data class SuccessfulConversationUnarchiveResponse(
        override val ok: Boolean) : ConversationUnarchiveResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/conversations.archive)
 */
data class ErrorConversationUnarchiveResponse constructor(
        override val ok: Boolean,
        @JsonProperty("error") val error: String
) : ConversationUnarchiveResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/conversations.archive)
 */
data class ConversationUnarchiveRequest(@JsonProperty("channel") val channel: String) {
    companion object
}
