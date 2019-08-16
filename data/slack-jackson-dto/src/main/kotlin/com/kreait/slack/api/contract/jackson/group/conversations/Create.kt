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
        JsonSubTypes.Type(value = SuccessfulConversationCreateResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorConversationCreateResponse::class, name = "false")
)
@JacksonDataClass
sealed class ConversationCreateResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * [SlackDoc](https://api.slack.com/methods/conversations.create)
 */
data class SuccessfulConversationCreateResponse(
        override val ok: Boolean,
        @JsonProperty("channel") val channel: Channel
) : ConversationCreateResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/conversations.create)
 */
@JacksonDataClass
data class ErrorConversationCreateResponse constructor(override val ok: Boolean,
                                                       @JsonProperty("error") val error: String)
    : ConversationCreateResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/conversations.create)
 */
data class ConversationCreateRequest(@JsonProperty("name") val name: String,
                                     @JsonProperty("is_private") val isPrivate: Boolean? = null) {
    companion object
}
