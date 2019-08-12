package com.kreait.slack.api.contract.jackson.group.conversations

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.JacksonDataClass

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

data class SuccessfulConversationSetPurposeResponse(
        override val ok: Boolean,
        @JsonProperty("purpose") val purpose: String
) : ConversationSetPurposeResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorConversationSetPurposeResponse constructor(
        override val ok: Boolean,
        @JsonProperty("error") val error: String
) : ConversationSetPurposeResponse(ok) {
    companion object
}

/**
 * Data Class that represents arguments as defined in https://api.slack.com/methods/conversations.setPurpose
 */
data class ConversationsSetPurposeRequest(private val channel: String,
                                          private val purpose: String) {
    companion object
}
