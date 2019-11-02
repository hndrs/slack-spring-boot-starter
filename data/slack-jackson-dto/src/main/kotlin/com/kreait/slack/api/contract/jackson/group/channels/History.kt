package com.kreait.slack.api.contract.jackson.group.channels

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.common.types.Message
import com.kreait.slack.api.contract.jackson.util.InstantToString
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import java.time.Instant

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulChannelsHistoryResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorChannelsHistoryResponse::class, name = "false")
)
@JacksonDataClass
sealed class ChannelsHistoryResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property messages list of history-messages
 * @property latestTimestamp timestamp of the latest message
 * @property hasMore boolean that determines if more messages are available
 */
@JacksonDataClass
data class SuccessfulChannelsHistoryResponse constructor(
    override val ok: Boolean,
    @JsonProperty("messages") val messages: List<Message>?,
    @InstantToString @JsonProperty("latest") val latestTimestamp: Instant?,
    @JsonProperty("has_more") val hasMore: Boolean?
) : ChannelsHistoryResponse(ok) {
    companion object
}


/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorChannelsHistoryResponse constructor(override val ok: Boolean,
                                                    @JsonProperty("error") val error: String)
    : ChannelsHistoryResponse(ok) {

    companion object
}

/**
 * Fetches history of messages and events from a channel.
 *
 * @property channelId Channel to fetch history for.
 * @property count Number of messages to return, between 1 and 1000.
 * @property inclusive Include messages with latest or oldest timestamp in results.
 * @property latestTimestamp End of time range of messages to include in results.
 * @property oldestTimestamp Start of time range of messages to include in results.
 * @property unreads true if the response should contain unread_count_display
 * @see [Slack Api Method](https://api.slack.com/methods/channels.history)
 */
@JacksonDataClass
data class ChannelsHistoryRequest constructor(@JsonProperty("channel") val channelId: String,
                                              @JsonProperty("count") val count: Int? = null,
                                              @JsonProperty("inclusive") val inclusive: Boolean? = null,
                                              @InstantToString @JsonProperty("latest") val latestTimestamp: Instant? = null,
                                              @InstantToString @JsonProperty("oldest") val oldestTimestamp: Instant? = null,
                                              @JsonProperty("unreads") val unreads: Boolean? = null
) {
    companion object {}

    fun toRequestMap(): Map<String, String> {
        val requestMap = mutableMapOf("channel" to channelId)
        count?.let { requestMap.put("count", it.toString()) }
        inclusive?.let { requestMap.put("inclusive", it.toString()) }
        latestTimestamp?.let { requestMap.put("latest", it.toString()) }
        oldestTimestamp?.let { requestMap.put("oldest", it.toString()) }
        unreads?.let { requestMap.put("unreads", it.toString()) }
        return requestMap

    }
}


