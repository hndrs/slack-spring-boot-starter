package com.kreait.slack.api.contract.jackson.group.conversations

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.JacksonDataClass
import com.kreait.slack.api.contract.jackson.common.ResponseMetadata
import java.time.Instant

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulConversationHistoryResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorConversationHistoryResponse::class, name = "false")
)
@JacksonDataClass
sealed class ConversationHistoryResponse constructor(@JsonProperty("ok") open val ok: Boolean)

data class SuccessfulConversationHistoryResponse(
        override val ok: Boolean,
        @JsonProperty("messages") val messages: List<Message>,
        @JsonProperty("has_more") val hasMore: Boolean,
        @JsonProperty("pin_count") val pinCount: Int,
        @JsonProperty("response_metadata") val responseMetadata: ResponseMetadata
) : ConversationHistoryResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorConversationHistoryResponse constructor(override val ok: Boolean,
                                                        @JsonProperty("error") val error: String)
    : ConversationHistoryResponse(ok) {
    companion object
}

@JacksonDataClass
data class Message(
        @JsonProperty("type") val type: String,
        @JsonProperty("user") val user: String,
        @JsonProperty("text") val text: String,
        @JsonProperty("ts") val timestamp: Instant,
        @JsonProperty("attachments") val attachments: List<Attachment>) {
    companion object {}

    @JacksonDataClass
    data class Attachment(
            @JsonProperty("service_name") val serviceName: String,
            @JsonProperty("text") val text: String,
            @JsonProperty("fallback") val fallback: String,
            @JsonProperty("thumb_url") val thumbUrl: String? = null,
            @JsonProperty("thumb_width") val thumbWidth: Int? = null,
            @JsonProperty("thumb_height") val thumbHeight: Int? = null,
            @JsonProperty("id") val id: Int) {
        companion object
    }
}


/**
 * DataClass that represents arguments as defined here https://api.slack.com/methods/conversations.history
 */
data class ConversationsHistoryRequest(private val channel: String,
                                       private val cursor: String? = null,
                                       private val inclusive: Boolean = false,
                                       private val latest: String? = null,
                                       private val limit: Int? = null,
                                       private val oldest: Int? = null) {

    fun toRequestMap(): MutableMap<String, String> {
        val requestMap = mutableMapOf<String, String>()
        channel.let { requestMap["channel"] = it }
        cursor?.let { requestMap["cursor"] = it }
        inclusive.let { requestMap["inclusive"] = it.toString() }
        latest?.let { requestMap["latest"] = it }
        limit?.let { requestMap["limit"] = it.toString() }
        oldest?.let { requestMap["oldest"] = it.toString() }
        return requestMap
    }

    companion object

}
