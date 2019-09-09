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
        JsonSubTypes.Type(value = SuccessfulConversationSetPurposeResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorConversationSetPurposeResponse::class, name = "false")
)

@JacksonDataClass
sealed class ConversationSetPurposeResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 */
data class SuccessfulConversationSetPurposeResponse(
        override val ok: Boolean,
        @JsonProperty("purpose") val purpose: String
) : ConversationSetPurposeResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorConversationSetPurposeResponse constructor(
        override val ok: Boolean,
        @JsonProperty("error") val error: String
) : ConversationSetPurposeResponse(ok) {
    companion object
}

/**
 * Sets the Purpose for a conversation.
 *
 * @property channel the channel-id of the channel you want to set the Purpose for
 * @property purpose the purpose you want to set
 */
data class ConversationsSetPurposeRequest(private val channel: String,
                                          private val purpose: String) {
    companion object
}
