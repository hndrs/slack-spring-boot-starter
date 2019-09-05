package com.kreait.slack.api.contract.jackson.common.types

import com.fasterxml.jackson.annotation.JsonProperty
import com.kreait.slack.api.contract.jackson.util.InstantToInt
import com.kreait.slack.api.contract.jackson.util.InstantToString
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import org.jetbrains.annotations.Nullable
import java.time.Instant

/**
 * A data class representing a Slack conversation.
 * A conversation is a channel-like thing, which can be a public channel, a private channel,
 * a direct-message or a multi-person direct-message.
 *
 * @property id The unique ID of the conversation.
 * @property name Indicates the name of the conversation.
 * @property isChannel Indicates if this conversation is a public channel.
 * @property isGroup Indicates if the conversation is a private channel. This means that isPrivate needs to be true.
 * @property isIm States that the conversation is is a direct-message between two users or a user and a bot.
 * @property createdAt A unix-timestamp indicating the date when the conversation was created.
 * @property createdBy Holds the user id of the conversation creator.
 * @property isArchived True, if the conversation is archived.
 * @property isGeneral True, if the conversation is in fact the 'general' discussion channel (regardless of the name), that includes all regular members.
 * @property unlinked Indicates the unlinked count.
 * @property nameNormalized Normalized name of the conversation.
 * @property isShared Indicates if the conversation is shared across workspaces.
 * @property isReadOnly Indicates if the conversation is read-only.
 * @property isExtShared Indicates if the conversation is shared with external workspaces.
 * @property isOrgShared Indicates if the conversation is shared between enterprise grid workspaces within the same organisation.
 * @property sharedTeamIds A List of team-ids that share the conversation.
 * @property pendingShared Holds a List of workspace-ids that are considered for sharing this conversation and are pending after an invite.
 * @property isPendingExtShared Indicates that the conversation is in the process of being a isExtShared channel.
 * @property isMember Indicates if the caller (may it be a user, a bot user or slack app) is itself a member of the conversation.
 * @property isPrivate States if the conversation is private between two or more users.
 * @property isMpim Represents an unnamed private conversation between users, acts like isPrivate.
 * @property topic Provides information about the conversation's [Topic].
 * @property purpose Provides information about the conversation's [Purpose].
 * @property previousNames A list of Strings containing all previous names of the conversation.
 * @property numMembers Indicates the member count of the channel.
 * @property lastReadAt The timestamp for the last message the calling user has read in this conversation.
 * @property isOpen Shows if the direct-message channel is open.
 * @property priority Indicates the conversation's priority.
 * @property user If this channel is a direct-message channel, this property indicates not the calling but the other user.
 * @property isUserDeleted True, if the other users account has been disabled.
 *
 * @see [Topic]
 * @see [Purpose]
 * @see [Channel]
 * @see [Slack API Documentation - Channel](https://api.slack.com/types/channel)
 * @see [Slack API Documentation - Conversation](https://api.slack.com/types/conversation)
 */
@JacksonDataClass
data class Conversation(
        @JsonProperty("id") val id: String,
        @JsonProperty("name") val name: String?,
        @JsonProperty("is_channel") val isChannel: Boolean?,
        @JsonProperty("is_group") val isGroup: Boolean?,
        @JsonProperty("is_im") val isIm: Boolean?,
        @InstantToInt @JsonProperty("created") val createdAt: Instant,
        @JsonProperty("creator") val createdBy: String?,
        @JsonProperty("is_archived") val isArchived: Boolean?,
        @JsonProperty("is_general") val isGeneral: Boolean?,
        @JsonProperty("unlinked") val unlinked: Int?,
        @JsonProperty("name_normalized") val nameNormalized: String?,
        @JsonProperty("is_shared") val isShared: Boolean?,
        @JsonProperty("is_read_only") val isReadOnly: Boolean?,
        @JsonProperty("is_ext_shared") val isExtShared: Boolean?,
        @JsonProperty("is_org_shared") val isOrgShared: Boolean?,
        @JsonProperty("shared_team_ids") val sharedTeamIds: List<String>?,
        @JsonProperty("pending_shared") val pendingShared: List<Any>?,
        @JsonProperty("is_pending_ext_shared") val isPendingExtShared: Boolean?,
        @JsonProperty("is_member") val isMember: Boolean?,
        @JsonProperty("is_private") val isPrivate: Boolean?,
        @JsonProperty("is_mpim") val isMpim: Boolean?,
        @JsonProperty("topic") val topic: Topic?,
        @JsonProperty("purpose") val purpose: Purpose?,
        @JsonProperty("previous_names") @Nullable val previousNames: List<Any>?,
        @JsonProperty("num_members") val numMembers: Int?,
        @InstantToString @JsonProperty("last_read") @Nullable val lastReadAt: Instant?,
        @JsonProperty("is_open") val isOpen: Boolean?,
        @JsonProperty("priority") val priority: Int?,
        @JsonProperty("user") val user: String?,
        @JsonProperty("is_user_deleted") val isUserDeleted: Boolean?) {
    companion object
}




