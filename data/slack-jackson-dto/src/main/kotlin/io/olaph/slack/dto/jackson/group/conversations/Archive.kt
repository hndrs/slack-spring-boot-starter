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
        JsonSubTypes.Type(value = SuccessfulConversationArchiveResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorConversationArchiveResponse::class, name = "false")
)

@JacksonDataClass
sealed class ConversationArchiveResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * [SlackDoc](https://api.slack.com/methods/conversations.archive)
 */
data class SuccessfulConversationArchiveResponse(
        override val ok: Boolean) : ConversationArchiveResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/conversations.archive)
 */
data class ErrorConversationArchiveResponse constructor(
        override val ok: Boolean,
        @JsonProperty("error") val error: String
) : ConversationArchiveResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/conversations.archive)
 */
data class ConversationArchiveRequest(@JsonProperty("channel") val channel: String) {
    companion object
}
