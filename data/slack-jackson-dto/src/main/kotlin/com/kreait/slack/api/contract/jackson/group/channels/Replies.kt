package com.kreait.slack.api.contract.jackson.group.channels

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.common.types.Message
import com.kreait.slack.api.contract.jackson.util.InstantToString
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import java.time.Instant

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "ok",
    visible = true
)

@JsonSubTypes(
    JsonSubTypes.Type(value = SuccessfulChannelsRepliesResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorChannelsRepliesResponse::class, name = "false")
)

@JacksonDataClass
sealed class ChannelsRepliesResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property messages the messages of the thread
 * @property hasMore determines if more messages exist
 */
@JacksonDataClass
data class SuccessfulChannelsRepliesResponse constructor(
    override val ok: Boolean,
    @JsonProperty("messages") val messages: List<Message>,
    @JsonProperty("has_more") val hasMore: Boolean = false
) : ChannelsRepliesResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorChannelsRepliesResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String,
    @JsonProperty("detail") val detail: String
) : ChannelsRepliesResponse(ok) {
    companion object
}

/**
 * Retrieve a thread of messages posted to a channel
 *
 * @property channelId Channel to fetch thread from
 * @property threadTimestamp Unique identifier of a thread's parent message
 */
@JacksonDataClass
data class ChannelsRepliesRequest constructor(
    @JsonProperty("channel") val channelId: String,
    @InstantToString @JsonProperty("thread_ts") val threadTimestamp: Instant
) {

    companion object {}

    fun toRequestMap() = mapOf("channel" to channelId, "thread_ts" to threadTimestamp.toString())
}
