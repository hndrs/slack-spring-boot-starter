package com.kreait.slack.api.contract.jackson.group.conversations

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.common.types.Channel
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

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
 * Success-response of this request.
 *
 * @property ok will be true
 */
@JacksonDataClass
data class SuccessfulConversationInviteResponse(
        override val ok: Boolean,
        @JsonProperty("channel") val channel: Channel
) : ConversationInviteResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorConversationInviteResponse constructor(override val ok: Boolean,
                                                       @JsonProperty("error") val error: String)
    : ConversationInviteResponse(ok) {
    companion object
}

/**
 * Adds a user to the Conversation
 *
 * @property channel the channel-id you want to add the user to
 * @property users the user-id which you want to add to the channel
 */
data class ConversationsInviteRequest(@JsonProperty("channel") val channel: String,
                                      @JsonProperty("users") val users: List<String>) {
    companion object
}
