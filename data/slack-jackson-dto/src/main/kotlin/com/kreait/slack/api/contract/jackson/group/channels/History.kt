package com.kreait.slack.api.contract.jackson.group.channels

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.JacksonDataClass
import java.time.Instant

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulChannelHistoryResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorChannelHistoryResponse::class, name = "false")
)
@JacksonDataClass
sealed class ChannelHistoryResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulChannelHistoryResponse constructor(override val ok: Boolean,
                                                        @JsonProperty("messages") val messages: List<Message?>?,
                                                        @JsonProperty("latest") val latest: Instant?,
                                                        @JsonProperty("has_more") val hasMore: Boolean?) : ChannelHistoryResponse(ok) {

    companion object {}

    data class Message(
            @JsonProperty("attachments") val attachments: List<Attachment?>?,
            @JsonProperty("bot_id") val botId: String?,
            @JsonProperty("is_starred") val isStarred: Boolean = false,
            @JsonProperty("reactions") val reactions: List<Reaction?>?,
            @JsonProperty("subtype") val subtype: String?,
            @JsonProperty("text") val text: String?,
            @JsonProperty("ts") val ts: Instant?,
            @JsonProperty("type") val type: String?,
            @JsonProperty("user") val user: String?,
            @JsonProperty("username") val username: String?
    ) {
        companion object
    }

    data class Reaction(
            @JsonProperty("count") val count: Int?,
            @JsonProperty("name") val name: String?,
            @JsonProperty("users") val users: List<String?>?
    ) {
        companion object
    }

    data class Attachment(
            @JsonProperty("fallback") val fallback: String?,
            @JsonProperty("id") val id: Int?,
            @JsonProperty("text") val text: String?
    ) {
        companion object
    }
}


@JacksonDataClass
data class ErrorChannelHistoryResponse constructor(override val ok: Boolean,
                                                   @JsonProperty("error") val error: String)
    : ChannelHistoryResponse(ok) {

    companion object
}

@JacksonDataClass
data class ChannelsHistoryRequest constructor(@JsonProperty("channel") val channel: String,
                                              @JsonProperty("count") val count: Int? = null,
                                              @JsonProperty("inclusive") val inclusive: Boolean? = null,
                                              @JsonProperty("latest") val latest: Instant? = null,
                                              @JsonProperty("oldest") val oldest: Instant? = null,
                                              @JsonProperty("unreads") val unreads: Boolean? = null
) {
    companion object {}

    fun toRequestMap(): Map<String, String> {
        val requestMap = mutableMapOf("channel" to channel)
        count?.let { requestMap.put("count", it.toString()) }
        inclusive?.let { requestMap.put("inclusive", it.toString()) }
        latest?.let { requestMap.put("latest", it.toString()) }
        oldest?.let { requestMap.put("oldest", it.toString()) }
        unreads?.let { requestMap.put("unreads", it.toString()) }
        return requestMap

    }
}


