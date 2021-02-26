package io.hndrs.slack.api.contract.jackson.common.types

import com.fasterxml.jackson.annotation.JsonProperty
import io.hndrs.slack.api.contract.jackson.common.messaging.Attachment
import io.hndrs.slack.api.contract.jackson.common.messaging.Block
import io.hndrs.slack.api.contract.jackson.common.messaging.Reaction
import io.hndrs.slack.api.contract.jackson.common.messaging.Reply
import io.hndrs.slack.api.contract.jackson.util.InstantToString
import io.hndrs.slack.api.contract.jackson.util.JacksonDataClass
import java.time.Instant

@JacksonDataClass
data class Message(
    @JsonProperty("type") val type: String? = null,
    @JsonProperty("subtype") val subtype: String? = null,
    @JsonProperty("text") val text: String? = null,
    @InstantToString @JsonProperty("ts") val timestamp: Instant,
    @JsonProperty("username") val username: String? = null,
    @JsonProperty("attachments") val attachments: List<Attachment>? = listOf(),
    @JsonProperty("blocks") val blocks: List<Block>? = listOf(),
    @JsonProperty("bot_id") val botId: String? = null,
    @JsonProperty("pinned_to") val pinnedTo: List<String>? = listOf(),
    @JsonProperty("is_starred") val isStarred: Boolean? = null,
    @JsonProperty("user") val user: String? = null,
    @InstantToString @JsonProperty("thread_ts") val threadTimestamp: Instant? = null,
    @JsonProperty("replies") val replies: Reply? = null,
    @JsonProperty("reactions") val reactions: List<Reaction>? = null,
    @JsonProperty("parent_user_id") val parentUserId: String? = null,
    @JsonProperty("subscribed") val subscribed: Boolean? = null,
    @JsonProperty("last_read") val lastRead: String? = null,
    @JsonProperty("unread_count") val unreadCount: Int? = null,
    @JsonProperty("reply_count") val replyCount: Int? = null
) {
    companion object
}
