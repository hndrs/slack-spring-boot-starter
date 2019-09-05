package com.kreait.slack.api.contract.jackson.common.types

import com.fasterxml.jackson.annotation.JsonProperty
import com.kreait.slack.api.contract.jackson.util.InstantToInt
import com.kreait.slack.api.contract.jackson.util.InstantToString
import java.time.Instant

/**
 * A data-class representing a group object.
 * Groups contain information about private channels.
 * A group is essentially the same thing as a private channel.
 *
 * @property createdAt A unix-timestamp indicating the date when the group was created.
 * @property createdBy Holds the user id of the group creator.
 * @property id The unique ID of the group.
 * @property isArchived True, if the group is archived.
 * @property isGroup Is true if the group is private. Mutual exclusive with isOpen.
 * @property isOpen Shows if the direct-message group is open.
 * @property lastReadAt The timestamp for the last message the calling user has read in this group.
 * @property latest The latest message of the channel.
 * @property members A list of member ids representing members in the channel.
 * @property name Indicates the name of the group.
 * @property purpose Provides information about the group's [Purpose].
 * @property topic Provides information about the group's [Topic].
 * @property unreadCount A full count of visible messages that the calling user has yet to read.
 * @property unreadCountDisplay Like unreadCount but only with messages that matter to the calling user (excluding join and leave messages).
 */
data class Group(
        @InstantToInt
        @JsonProperty("created")
        val createdAt: Instant,
        @JsonProperty("creator")
        val createdBy: String?,
        @JsonProperty("id")
        val id: String?,
        @JsonProperty("is_archived")
        val isArchived: Boolean,
        @JsonProperty("is_group")
        val isGroup: Boolean = true,
        @JsonProperty("is_open")
        val isOpen: Boolean,
        @InstantToString
        @JsonProperty("last_read")
        val lastReadAt: Instant?,
        @JsonProperty("latest")
        val latest: Any?,
        @JsonProperty("members")
        val members: List<String>?,
        @JsonProperty("name")
        val name: String?,
        @JsonProperty("purpose")
        val purpose: Purpose?,
        @JsonProperty("topic")
        val topic: Topic?,
        @JsonProperty("unread_count")
        val unreadCount: Int?,
        @JsonProperty("unread_count_display")
        val unreadCountDisplay: Int?) {
    companion object
}
