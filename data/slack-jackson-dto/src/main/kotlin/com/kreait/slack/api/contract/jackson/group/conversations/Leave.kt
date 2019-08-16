package com.kreait.slack.api.contract.jackson.group.conversations

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulConversationLeaveResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorConversationLeaveResponse::class, name = "false")
)
@JacksonDataClass
sealed class ConversationsLeaveResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * [SlackDoc](https://api.slack.com/methods/conversations.leave)
 */
data class SuccessfulConversationLeaveResponse(
        override val ok: Boolean,
        @JsonProperty("not_in_channel") val notInChannel: Boolean) : ConversationsLeaveResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/conversations.leave)
 */
@JacksonDataClass
data class ErrorConversationLeaveResponse constructor(override val ok: Boolean,
                                                      @JsonProperty("error") val error: String)
    : ConversationsLeaveResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/conversations.leave)
 */
data class ConversationsLeaveRequest(@JsonProperty("channel") val channel: String) {
    companion object
}
