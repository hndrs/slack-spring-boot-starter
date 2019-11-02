package com.kreait.slack.api.contract.jackson.group.im

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.common.types.Message
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true
)

@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulImRepliesResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorImRepliesResponse::class, name = "false")
)

@JacksonDataClass
sealed class ImRepliesResponse constructor(@JsonProperty(value = "ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property messages list of thread-messages
 */
@JacksonDataClass
data class SuccessfulImRepliesResponse constructor(
    override val ok: Boolean,
    @JsonProperty(value = "messages") val messages: List<Message>
) : ImRepliesResponse(ok) {

    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorImRepliesResponse constructor(override val ok: Boolean,
                                              @JsonProperty(value = "error") val error: String)
    : ImRepliesResponse(ok) {
    companion object
}

/**
 * Fetches thread-messages of a message in a direct-message channel
 *
 * @property channel the channel-id of the direct-message channel that contains the thread
 * @property thread_ts the timestamp of the thread
 */
data class ImRepliesRequest(private val channel: String,
                            private val thread_ts: String) {

    companion object

    fun toRequestMap(): MutableMap<String, String> {
        val requestMap = mutableMapOf<String, String>()
        channel.let { requestMap.put("channel", it) }
        thread_ts.let { requestMap.put("thread_ts", it) }
        return requestMap
    }
}
