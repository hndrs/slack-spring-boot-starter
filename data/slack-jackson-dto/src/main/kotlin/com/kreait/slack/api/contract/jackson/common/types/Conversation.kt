package com.kreait.slack.api.contract.jackson.common.types

import com.fasterxml.jackson.annotation.JsonProperty
import com.kreait.slack.api.contract.jackson.util.InstantToInt
import com.kreait.slack.api.contract.jackson.util.InstantToString
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import org.jetbrains.annotations.Nullable
import java.time.Instant

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




