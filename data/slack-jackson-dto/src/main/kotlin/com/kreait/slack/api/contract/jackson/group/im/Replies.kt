package com.kreait.slack.api.contract.jackson.group.im

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.JacksonDataClass
import java.time.Instant

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

@JacksonDataClass
data class SuccessfulImRepliesResponse constructor(override val ok: Boolean,
                                                   @JsonProperty(value = "messages") val messages: List<Message>)
    : ImRepliesResponse(ok) {

    companion object {}

    data class Message(
            @JsonProperty("type") val type: String,
            @JsonProperty("ts") val timestamp: Instant,
            @JsonProperty("user") val user: String? = null,
            @JsonProperty("text") val text: String? = null,
            @JsonProperty("is_starred") val isStarred: Boolean? = false) {
        companion object
    }
}

@JacksonDataClass
data class ErrorImRepliesResponse constructor(override val ok: Boolean,
                                              @JsonProperty(value = "error") val error: String)
    : ImRepliesResponse(ok) {
    companion object
}

/**
 * DataClass that represents arguments as defined in https://api.slack.com/methods/im.replies
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
