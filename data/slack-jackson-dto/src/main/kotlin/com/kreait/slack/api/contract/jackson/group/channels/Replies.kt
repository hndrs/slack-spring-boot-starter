package com.kreait.slack.api.contract.jackson.group.channels

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.util.InstantToString
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import java.time.Instant

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)

@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulChannelsRepliesResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorChannelsRepliesResponse::class, name = "false")
)

@JacksonDataClass
sealed class ChannelsRepliesResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulChannelsRepliesResponse constructor(override val ok: Boolean,
                                                         @JsonProperty("messages") val channel: List<Message>,
                                                         @JsonProperty("has_more") val hasMore: Boolean = false)
    : ChannelsRepliesResponse(ok) {
    companion object {}


    //TODO this seems to be a common object
    data class Message(
            @InstantToString @JsonProperty("last_read") val lastReadAt: Instant?,
            @JsonProperty("parent_user_id") val parentUserId: String?,
            @JsonProperty("replies") val replies: List<Reply?>?,
            @JsonProperty("reply_count") val replyCount: Int?,
            @JsonProperty("subscribed") val subscribed: Boolean?,
            @JsonProperty("text") val text: String?,
            @InstantToString @JsonProperty("thread_ts") val threadTimestamp: Instant?,
            @InstantToString @JsonProperty("ts") val timestamp: Instant?,
            @JsonProperty("type") val type: String?,
            @JsonProperty("unread_count") val unreadCount: Int?,
            @JsonProperty("user") val user: String?
    ) {
        companion object
    }

    data class Reply(
            @InstantToString @JsonProperty("ts") val timestamp: Instant?,
            @JsonProperty("user") val user: String?
    ) {
        companion object
    }
}


@JacksonDataClass
data class ErrorChannelsRepliesResponse constructor(override val ok: Boolean,
                                                    @JsonProperty("error") val error: String,
                                                    @JsonProperty("detail") val detail: String)
    : ChannelsRepliesResponse(ok) {
    companion object
}


@JacksonDataClass
data class ChannelsRepliesRequest constructor(@JsonProperty("channel") val channelId: String,
                                              @InstantToString @JsonProperty("thread_ts") val threadTimestamp: Instant) {

    companion object {}

    fun toRequestMap() = mapOf("channel" to channelId, "thread_ts" to threadTimestamp.toString())
}
