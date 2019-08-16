package com.kreait.slack.api.contract.jackson.group.conversations

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import com.kreait.slack.api.contract.jackson.common.types.Channel

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulConversationInviteResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorConversationInviteResponse::class, name = "false")
)
@JacksonDataClass
sealed class ConversationInviteResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * [SlackDoc](https://api.slack.com/methods/conversations.invite)
 */
@JacksonDataClass
data class SuccessfulConversationInviteResponse(
        override val ok: Boolean,
        @JsonProperty("channel") val channel: Channel
) : ConversationInviteResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/conversations.invite)
 */
@JacksonDataClass
data class ErrorConversationInviteResponse constructor(override val ok: Boolean,
                                                       @JsonProperty("error") val error: String)
    : ConversationInviteResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/conversations.invite)
 */
data class ConversationsInviteRequest(@JsonProperty("channel") val channel: String,
                                      @JsonProperty("users") val users: List<String>) {
    companion object
}
