package io.olaph.slack.dto.jackson.common.types

import com.fasterxml.jackson.annotation.JsonProperty
import io.olaph.slack.dto.jackson.JacksonDataClass
import io.olaph.slack.dto.jackson.group.users.User
import org.jetbrains.annotations.Nullable

@JacksonDataClass
data class Conversation(
        @JsonProperty("id") val id: String,
        @JsonProperty("name") val name: String,
        @JsonProperty("is_channel") val isChannel: Boolean,
        @JsonProperty("is_group") val isGroup: Boolean,
        @JsonProperty("is_im") val isIm: Boolean,
        @JsonProperty("created") val created: Int,
        @JsonProperty("is_archived") val isArchived: Boolean,
        @JsonProperty("is_general") val isGeneral: Boolean,
        @JsonProperty("unlinked") val unlinked: Int,
        @JsonProperty("name_normalized") val nameNormalized: String,
        @JsonProperty("is_shared") val isShared: Boolean?,
        @JsonProperty("creator") val createdBy: String,
        @JsonProperty("is_ext_shared") val isExtShared: Boolean?,
        @JsonProperty("is_org_shared") val isOrgShared: Boolean?,
        @JsonProperty("shared_team_ids") val sharedTeamIds: List<String>?,
        @JsonProperty("pending_shared") val pendingShared: List<Any>?,
        @JsonProperty("is_pending_ext_shared") val isPendingExtShared: Boolean?,
        @JsonProperty("is_member") val isMember: Boolean?,
        @JsonProperty("is_private") val isPrivate: Boolean,
        @JsonProperty("is_mpim") val isMpim: Boolean?,
        @JsonProperty("topic") val topic: Topic?,
        @JsonProperty("purpose") val purpose: Purpose?,
        @JsonProperty("previous_names") @Nullable val previousNames: List<Any>?,
        @JsonProperty("num_members") val numMembers: Int?,
        @JsonProperty("last_read") @Nullable val lastRead: String?,
        @JsonProperty("is_open") val isOpen: Boolean,
        @JsonProperty("priority") val priority: Int,
        @JsonProperty("user") val user: String?,
        @JsonProperty("is_user_deletec") val isUserDeleted: Boolean?) {

    companion object

    @JacksonDataClass
    data class Purpose(
            @JsonProperty("value") val value: String,
            @JsonProperty("creator") val createdBy: String,
            @JsonProperty("last_set") val lastSet: Int
    )

    @JacksonDataClass
    data class Topic(
            @JsonProperty("value") val value: String,
            @JsonProperty("creator") val createdBy: String,
            @JsonProperty("last_set") val lastSet: Int
    )
}




