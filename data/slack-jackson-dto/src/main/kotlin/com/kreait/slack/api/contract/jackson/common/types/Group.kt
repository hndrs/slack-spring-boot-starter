package com.kreait.slack.api.contract.jackson.common.types

import com.fasterxml.jackson.annotation.JsonProperty
import com.kreait.slack.api.contract.jackson.util.InstantToInt
import com.kreait.slack.api.contract.jackson.util.InstantToString
import java.time.Instant

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
        val unreadCountDisplay: Int?
) {
    companion object
}
