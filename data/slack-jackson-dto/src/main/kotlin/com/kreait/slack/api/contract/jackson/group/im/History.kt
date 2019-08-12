package com.kreait.slack.api.contract.jackson.group.im

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.JacksonDataClass

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

@JacksonDataClass
data class SuccessfulImHistoryResponse constructor(override val ok: Boolean,
                                                   val latest: String,
                                                   val messages: List<Message>,
                                                   val hasMore: Boolean,
                                                   val isLimited: Boolean? = false)
    : ImHistoryResponse(ok) {
    companion object {}

    data class Message(
            @JsonProperty("type") val type: String,
            @JsonProperty("ts") val ts: String,
            @JsonProperty("user") val user: String? = null,
            @JsonProperty("text") val text: String? = null,
            @JsonProperty("is_starred") val isStarred: Boolean? = false) {
        companion object
    }
}

@JacksonDataClass
data class ErrorImHistoryResponse constructor(override val ok: Boolean,
                                              val error: String)
    : ImHistoryResponse(ok) {
    companion object
}

data class ImHistoryRequest(private val channel: String,
                            private val count: Int? = null,
                            private val inclusive: Boolean? = false,
                            private val latest: String? = null,
                            private val oldest: String? = null,
                            private val unreads: Boolean? = false) {
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
