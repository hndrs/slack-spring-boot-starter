package com.kreait.slack.api.contract.jackson.group.conversations

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.common.ResponseMetadata
import com.kreait.slack.api.contract.jackson.util.InstantToString
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
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

/**
 * Success-response of this request.
 *
 * @property ok will be true
 */
data class SuccessfulConversationHistoryResponse(
        override val ok: Boolean,
        @JsonProperty("messages") val messages: List<Message>,
        @JsonProperty("has_more") val hasMore: Boolean,
        @JsonProperty("pin_count") val pinCount: Int,
        @JsonProperty("response_metadata") val responseMetadata: ResponseMetadata
) : ConversationHistoryResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
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
        @InstantToString @JsonProperty("ts") val timestamp: Instant,
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
 * Fetches a conversation's history of messages and events.
 *
 * @property channel The channel id you want to fetch messages from
 * @property cursor Paginate through collections of data by setting the cursor parameter to a next_cursor attribute returned by a previous request's response_metadata. Default value fetches the first "page" of the collection.
 * @property inclusive Include messages with latest or oldest timestamp in results only when either timestamp is specified.
 * @property latest End of time range of messages to include in results.
 * @property limit The maximum number of items to return.
 * @property oldest Start of time range of messages to include in results.
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
