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
        JsonSubTypes.Type(value = SuccessfulChannelRepliesResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorChannelRepliesResponse::class, name = "false")
)

@JacksonDataClass
sealed class ChannelRepliesResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulChannelRepliesResponse constructor(override val ok: Boolean,
                                                        @JsonProperty("messages") val channel: List<Message>,
                                                        @JsonProperty("has_more") val hasMore: Boolean = false)
    : ChannelRepliesResponse(ok) {
    companion object {}


    data class Message(
            @JsonProperty("last_read") val lastRead: String?,
            @JsonProperty("parent_user_id") val parentUserId: String?,
            @JsonProperty("replies") val replies: List<Reply?>?,
            @JsonProperty("reply_count") val replyCount: Int?,
            @JsonProperty("subscribed") val subscribed: Boolean?,
            @JsonProperty("text") val text: String?,
            @JsonProperty("thread_ts") val threadTs: String?,
            @JsonProperty("ts") val ts: String?,
            @JsonProperty("type") val type: String?,
            @JsonProperty("unread_count") val unreadCount: Int?,
            @JsonProperty("user") val user: String?
    ) {
        companion object
    }

    data class Reply(
            @JsonProperty("ts") val ts: String?,
            @JsonProperty("user") val user: String?
    ) {
        companion object
    }
}


@JacksonDataClass
data class ErrorChannelRepliesResponse constructor(override val ok: Boolean,
                                                   @JsonProperty("error") val error: String,
                                                   @JsonProperty("detail") val detail: String)
    : ChannelRepliesResponse(ok) {
    companion object
}


@JacksonDataClass
data class ChannelRepliesRequest constructor(@JsonProperty("channel") val channel: String,
                                             @JsonProperty("thread_ts") val threadTimestamp: Instant) {

    companion object {}

    fun toRequestMap() = mapOf("channel" to channel, "thread_ts" to threadTimestamp.toString())
}
