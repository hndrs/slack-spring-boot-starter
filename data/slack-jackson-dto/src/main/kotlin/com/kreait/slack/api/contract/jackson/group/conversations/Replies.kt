package com.kreait.slack.api.contract.jackson.group.conversations

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.common.ResponseMetadata
import com.kreait.slack.api.contract.jackson.common.types.Message
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "ok",
    visible = true
)
@JsonSubTypes(
    JsonSubTypes.Type(value = SuccessfulConversationRepliesResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorConversationRepliesResponse::class, name = "false")
)
@JacksonDataClass
sealed class ConversationRepliesResponse constructor(@JsonProperty("ok") open val ok: Boolean) {
    companion object
}

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property messages the messages of the requested thread
 * @property hasMore determines if more messages are available
 * @property responseMetadata contains additional information, used for paging
 */
@JacksonDataClass
data class SuccessfulConversationRepliesResponse constructor(
    override val ok: Boolean,
    @JsonProperty("messages") val messages: List<Message>,
    @JsonProperty("has_more") val hasMore: Boolean,
    @JsonProperty("response_metadata") val responseMetadata: ResponseMetadata
) : ConversationRepliesResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorConversationRepliesResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : ConversationRepliesResponse(ok) {
    companion object
}

/**
 * Retrieve a thread of messages posted to a conversation
 *
 * @property channelId the channel-id of the channel that contains the thread
 * @property timestamp the timestamp of the message
 * @property cursor Paginate through collections of data by setting the cursor parameter to a next_cursor attribute returned by a previous request's response_metadata. Default value fetches the first "page" of the collection.
 * @property inclusive Include messages with latest or oldest timestamp in results only when either timestamp is specified.
 * @property latest End of time range of messages to include in results.
 * @property limit The maximum number of items to return. Fewer than the requested number of items may be returned, even if the end of the users list hasn't been reached.
 * @property oldest Start of time range of messages to include in results.
 */
data class ConversationsRepliesRequest constructor(
    val channelId: String,
    val timestamp: String,
    val cursor: String? = null,
    val inclusive: Boolean? = null,
    val latest: String? = null,
    val limit: Int? = null,
    val oldest: String? = null
) {

    fun toRequestMap(): MutableMap<String, String> {
        val requestMap = mutableMapOf<String, String>()
        requestMap["channel"] = channelId
        requestMap["ts"] = timestamp
        cursor?.let { requestMap["cursor"] = it }
        inclusive?.let { requestMap["inclusive"] = it.toString() }
        latest?.let { requestMap["latest"] = it }
        oldest?.let { requestMap["oldest"] = it }
        limit?.let { requestMap["limit"] = it.toString() }
        return requestMap
    }

    companion object
}


