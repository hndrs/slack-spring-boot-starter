package io.olaph.slack.dto.jackson.common.types

import com.fasterxml.jackson.annotation.JsonProperty
import io.olaph.slack.dto.jackson.JacksonDataClass
import io.olaph.slack.dto.jackson.group.chat.Message

@JacksonDataClass
data class Channel(
        @JsonProperty("id") val id: String,
        @JsonProperty("name") val name: String,
        @JsonProperty("is_channel") val isChannel: Boolean,
        //TODO make this an INSTANT
        @JsonProperty("created") val created: Int,
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
        //TODO make this an INSTANT
        @JsonProperty("last_read") val lastRead: String?,
        @JsonProperty("latest") val latest: Message? = null,
        @JsonProperty("unread_count") val unreadCount: Int,
        @JsonProperty("unread_count_display") val unreadCountDisplay: Int,
        @JsonProperty("members") val members: List<String>? = null,
        @JsonProperty("topic") val topic: Topic,
        @JsonProperty("purpose") val purpose: Purpose,
        @JsonProperty("previous_names") val previousNames: List<String>,
        @JsonProperty("num_members") val numMembers: Int,
        @JsonProperty("locale") val locale: String? = null,
        @JsonProperty("user") val user: String? = null,
        @JsonProperty("is_open") val isOpen: Boolean = false,
        @JsonProperty("priority") val priority: Double? = 0.0) {
    companion object

    @JacksonDataClass
    data class Purpose(@JsonProperty("value") val value: String,
                       @JsonProperty("creator") val creator: String,
                       @JsonProperty("last_set") val lastSet: Int) {

        companion object
    }

    @JacksonDataClass
    data class Topic(@JsonProperty("value") val value: String,
                     @JsonProperty("creator") val creator: String,
                     @JsonProperty("last_set") val lastSet: Int) {
        companion object
    }
}



