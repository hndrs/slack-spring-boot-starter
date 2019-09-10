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
        JsonSubTypes.Type(value = SuccessfulConversationSetTopicResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorConversationSetTopicResponse::class, name = "false")
)

@JacksonDataClass
sealed class ConversationSetTopicResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property topic the new topic
 */
data class SuccessfulConversationSetTopicResponse(
        override val ok: Boolean,
        @JsonProperty("topic") val topic: String
) : ConversationSetTopicResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorConversationSetTopicResponse constructor(
        override val ok: Boolean,
        @JsonProperty("error") val error: String
) : ConversationSetTopicResponse(ok) {
    companion object
}

/**
 * Sets the topic for a conversation.
 *
 * @property channel the channel-id of the channel you want to set the topic for
 * @property purpose the topic you want to set
 */
data class ConversationsSetTopicRequest(private val channel: String,
                                        private val topic: String) {
    companion object
}
