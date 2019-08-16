package com.kreait.slack.api.contract.jackson.common.types

import com.fasterxml.jackson.annotation.JsonProperty

data class Group(
        @JsonProperty("created")
        val created: Int?,
        @JsonProperty("creator")
        val creator: String?,
        @JsonProperty("id")
        val id: String?,
        @JsonProperty("is_archived")
        val isArchived: Boolean,
        @JsonProperty("is_group")
        val isGroup: Boolean = true,
        @JsonProperty("is_open")
        val isOpen: Boolean,
        @JsonProperty("last_read")
        val lastRead: String?,
        @JsonProperty("latest")
        val latest: Any?,
        @JsonProperty("members")
        val members: List<String?>?,
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

data class Topic(
        @JsonProperty("creator") val creator: String?,
        @JsonProperty("last_set") val lastSet: Int?,
        @JsonProperty("value") val value: String?
) {
    companion object
}

data class Purpose(
        @JsonProperty("creator") val creator: String?,
        @JsonProperty("last_set") val lastSet: Int?,
        @JsonProperty("value") val value: String?
) {
    companion object
}