package com.kreait.slack.api.contract.jackson.group.im

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.common.types.Message
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "ok",
    visible = true)
@JsonSubTypes(
    JsonSubTypes.Type(value = SuccessfulImHistoryResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorImHistoryResponse::class, name = "false")
)

@JacksonDataClass
sealed class ImHistoryResponse constructor(@JsonProperty(value = "ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property latest the timestamp of the latest message
 * @property messages list of past messages
 * @property hasMore determines if more messages are available
 * @property isLimited only included for free teams that have reached the free message limit
 */
@JacksonDataClass
data class SuccessfulImHistoryResponse constructor(
    override val ok: Boolean,
    val latest: String,
    val messages: List<Message>,
    val hasMore: Boolean,
    val isLimited: Boolean? = false
) : ImHistoryResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorImHistoryResponse(
    override val ok: Boolean,
    val error: String
) : ImHistoryResponse(ok) {
    companion object
}

/**
 * Fetches history messages of an im-channel
 *
 * @property channel the channel id of the channel you want to fetch history-messages from
 * @property count the amoutn of messages to fetch
 * @property inclusive Include messages with latest or oldest timestamp in results.
 * @property latest End of time range of messages to include in results.
 * @property oldest Start of time range of messages to include in results.
 * @property unreads determines if unread_count_display should be included in the output
 */
data class ImHistoryRequest(
    private val channel: String,
    private val count: Int? = null,
    private val inclusive: Boolean? = false,
    private val latest: String? = null,
    private val oldest: String? = null,
    private val unreads: Boolean? = false
) {
    companion object {}

    fun toRequestMap(): MutableMap<String, String> {
        val requestMap = mutableMapOf("channel" to channel)
        count?.let { requestMap.put("count", it.toString()) }
        inclusive?.let { requestMap.put("inclusive", it.toString()) }
        latest?.let { requestMap.put("latest", it) }
        oldest?.let { requestMap.put("oldest", it) }
        unreads?.let { requestMap.put("unreads", it.toString()) }
        return requestMap
    }
}
