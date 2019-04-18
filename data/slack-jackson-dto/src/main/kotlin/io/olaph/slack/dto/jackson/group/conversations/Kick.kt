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
        JsonSubTypes.Type(value = SuccessfulConversationKickResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorConversationKickResponse::class, name = "false")
)
@JacksonDataClass
sealed class ConversationsKickResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * [SlackDoc](https://api.slack.com/methods/conversations.kick)
 */
data class SuccessfulConversationKickResponse(
        override val ok: Boolean) : ConversationsKickResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/conversations.kick)
 */
@JacksonDataClass
data class ErrorConversationKickResponse constructor(override val ok: Boolean,
                                                      @JsonProperty("error") val error: String)
    : ConversationsKickResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/conversations.kick)
 */
data class ConversationsKickRequest(@JsonProperty("channel") val channel: String,
                                    @JsonProperty("user") val user: String) {
    companion object
}
