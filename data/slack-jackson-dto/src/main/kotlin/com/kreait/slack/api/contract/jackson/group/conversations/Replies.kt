package com.kreait.slack.api.contract.jackson.group.conversations

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.JacksonDataClass
import com.kreait.slack.api.contract.jackson.common.ResponseMetadata
import java.time.Instant

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulConversationRepliesResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorConversationRepliesResponse::class, name = "false")
)
@JacksonDataClass
sealed class ConversationRepliesResponse constructor(@JsonProperty("ok") open val ok: Boolean) {
    companion object
}

@JacksonDataClass
data class SuccessfulConversationRepliesResponse constructor(override val ok: Boolean,
                                                             @JsonProperty("messages") val messages: List<Message>,
                                                             @JsonProperty("has_more") val hasMore: Boolean,
                                                             @JsonProperty("response_metadata") val responseMetadata: ResponseMetadata
) : ConversationRepliesResponse(ok) {

    companion object

    @JacksonDataClass
    data class Message(
            @JsonProperty("type") val type: String,
            @JsonProperty("ts") val timestamp: Instant,
            @JsonProperty("user") val user: String,
            @JsonProperty("text") val text: String,
            @JsonProperty("thread_ts") val threadTimestamp: String? = null,
            @JsonProperty("parent_user_id") val parentUserId: String? = null,
            @JsonProperty("subscribed") val subscribed: Boolean? = null,
            @JsonProperty("last_read") val lastRead: String? = null,
            @JsonProperty("replies") val replies: Reply? = null,

            @JsonProperty("unread_count") val unreadCount: Int? = null) {
        companion object
    }

    @JacksonDataClass
    data class Reply(@JsonProperty("user") val userId: String,
                     @JsonProperty("ts") val timestamp: Instant)
}

@JacksonDataClass
data class ErrorConversationRepliesResponse constructor(override val ok: Boolean, @JsonProperty("error") val error: String)
    : ConversationRepliesResponse(ok) {
    companion object
}

data class ConversationsRepliesRequest constructor(val channelId: String,
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


