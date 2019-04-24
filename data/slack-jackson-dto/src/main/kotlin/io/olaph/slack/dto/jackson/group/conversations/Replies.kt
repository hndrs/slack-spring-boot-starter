package io.olaph.slack.dto.jackson.group.conversations

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.olaph.slack.dto.jackson.JacksonDataClass
import io.olaph.slack.dto.jackson.common.ResponseMetadata

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
            @JsonProperty("ts") val ts: String,
            @JsonProperty("user") val user: String,
            @JsonProperty("text") val text: String,
            @JsonProperty("thread_ts") val threadTimestamp: String? = null,
            @JsonProperty("parent_user_id") val parentUserId: String? = null,
            @JsonProperty("subscribed") val subscribed: Boolean? = null,
            @JsonProperty("last_read") val lastRead: String? = null,
            @JsonProperty("unread_count") val unreadCount: Int? = null) {
        companion object
    }
}

@JacksonDataClass
data class ErrorConversationRepliesResponse constructor(override val ok: Boolean, @JsonProperty("error") val error: String)
    : ConversationRepliesResponse(ok) {
    companion object
}

@JacksonDataClass
data class ConversationsRepliesRequest constructor(@JsonProperty("channel") val channelId: String,
                                                   @JsonProperty("ts") val timestamp: String,
                                                   @JsonProperty("cursor") val cursor: String? = null,
                                                   @JsonProperty("inclusive") val inclusive: Boolean? = null,
                                                   @JsonProperty("latest") val latest: String? = null,
                                                   @JsonProperty("limit") val limit: Int? = null,
                                                   @JsonProperty("oldest") val oldest: String? = null
) {
    companion object
}


