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
        JsonSubTypes.Type(value = SuccessfulConversationSetTopicResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorConversationSetTopicResponse::class, name = "false")
)

@JacksonDataClass
sealed class ConversationSetTopicResponse constructor(@JsonProperty("ok") open val ok: Boolean)

data class SuccessfulConversationSetTopicResponse(
        override val ok: Boolean,
        @JsonProperty("topic") val topic: String
) : ConversationSetTopicResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorConversationSetTopicResponse constructor(
        override val ok: Boolean,
        @JsonProperty("error") val error: String
) : ConversationSetTopicResponse(ok) {
    companion object
}

/**
 * Data Class that represents arguments as defined in https://api.slack.com/methods/conversations.setTopic
 */
data class ConversationsSetTopicRequest(private val channel: String,
                                        private val topic: String) {
    companion object
}
