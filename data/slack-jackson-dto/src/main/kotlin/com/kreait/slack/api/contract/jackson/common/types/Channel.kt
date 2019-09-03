package com.kreait.slack.api.contract.jackson.common.types

import com.fasterxml.jackson.annotation.JsonProperty
import com.kreait.slack.api.contract.jackson.group.chat.Message
import com.kreait.slack.api.contract.jackson.util.InstantToInt
import com.kreait.slack.api.contract.jackson.util.InstantToString
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import java.time.Instant

/**
 * A data-class representing a Slack channel.
 * A channel object contains information about a workspace channel.
 *
 * @property id The unique ID of the channel.
 * @property name Indicates the name of the channel.
 * @property isChannel Indicates if this channel is a public channel.
 * @property createdAt A unix-timestamp indicating the date when the channel was created.
 * @property createdBy Holds the user id of the channel creator.
 * @property isArchived True, if the channel is archived.
 * @property isGeneral True, if the channel is the '#general' channel, that includes all regular members.
 * @property unlinked Indicates the unlinked count.
 * @property nameNormalized Normalized name of the channel.
 * @property isShared Indicates if the channel is shared across workspaces.
 * @property isOrgShared Indicates if the channel is shared between enterprise grid workspaces within the same organisation.
 * @property isMember True, if the calling member is part of the channel.
 * @property isPrivate Indicates if the channel is a private channel.
 * @property isMpim Indicates if a multipart im is being emulated as a private channel.
 * @property lastReadAt The timestamp for the last message the calling user has read in this channel.
 * @property latest The latest [Message] of the channel.
 * @property unreadCount A full count of visible messages that the calling user has yet to read.
 * @property unreadCountDisplay Like unreadCount but only with messages that matter to the calling user (excluding join and leave messages).
 * @property members A list of member ids representing members in the channel.
 * @property topic Provides information about the channel's [Topic].
 * @property purpose Provides information about the channel's [Purpose].
 * @property previousNames A list of Strings containing all previous names of the channel.
 * @property numMembers Indicates the member count of the channel.
 * @property locale The locale of the channel.
 * @property user If this channel is a direct-message channel, this property indicates not the calling but the other user.
 * @property isOpen Shows if the direct-message channel is open.
 * @property priority Indicates the channel's priority.
 *
 * @see [Topic]
 * @see [Purpose]
 * @see [Conversation]
 * @see [Slack API Documentation - Channel](https://api.slack.com/types/channel)
 * @see [Slack API Documentation - Conversation](https://api.slack.com/types/conversation)
 */
@JacksonDataClass
data class Channel(
        @JsonProperty("id") val id: String,
        @JsonProperty("name") val name: String,
        @JsonProperty("is_channel") val isChannel: Boolean,
        @InstantToInt @JsonProperty("created") val createdAt: Instant,
        @JsonProperty("creator") val createdBy: String,
        @JsonProperty("is_archived") val isArchived: Boolean,
        @JsonProperty("is_general") val isGeneral: Boolean,
        @JsonProperty("unlinked") val unlinked: Int,
        @JsonProperty("name_normalized") val nameNormalized: String,
        @JsonProperty("is_shared") val isShared: Boolean,
        @JsonProperty("is_org_shared") val isOrgShared: Boolean,
        @JsonProperty("is_member") val isMember: Boolean,
        @JsonProperty("is_private") val isPrivate: Boolean,
        @JsonProperty("is_mpim") val isMpim: Boolean,
        @InstantToString @JsonProperty("last_read") val lastReadAt: Instant?,
        @JsonProperty("latest") val latestMessage: Message? = null,
        @JsonProperty("unread_count") val unreadCount: Int,
        @JsonProperty("unread_count_display") val unreadCountDisplay: Int,
        @JsonProperty("members") val members: List<String>? = null,
        @JsonProperty("topic") val topic: Topic,
        @JsonProperty("purpose") val purpose: Purpose,
        @JsonProperty("previous_names") val previousNames: List<String>,
        @JsonProperty("num_members") val numMembers: Int?,
        @JsonProperty("locale") val locale: String? = null,
        @JsonProperty("user") val user: String? = null,
        @JsonProperty("is_open") val isOpen: Boolean = false,
        @JsonProperty("priority") val priority: Double? = 0.0) {
    companion object
}



