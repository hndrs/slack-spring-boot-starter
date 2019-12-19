package com.kreait.slack.api.contract.jackson.event

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.kreait.slack.api.contract.jackson.common.types.Member
import com.kreait.slack.api.contract.jackson.group.usergroups.UserGroup
import com.kreait.slack.api.contract.jackson.util.InstantToInt
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import java.time.Instant


interface EventData {
    fun toMap(): Map<String, Any> = jacksonObjectMapper().convertValue(this, object : TypeReference<Map<String, Any>>() {})
}


@JacksonDataClass
data class TeamJoin(@JsonProperty("user") val member: Member) : EventData {
    companion object {
        /**
         * A new member has joined
         * https://api.slack.com/events/team_join
         */
        const val TYPE = "team_join"
    }

    override fun toMap(): Map<String, Any> {

        return jacksonObjectMapper().convertValue(this, object : TypeReference<Map<String, Any>>() {})
    }
}

@JacksonDataClass
data class UserChange(@JsonProperty("user") val member: Member) : EventData {
    companion object {
        /**
         * A member's data has changed
         * https://api.slack.com/events/user_change
         */
        const val TYPE = "user_change"
    }
}

@JacksonDataClass
data class SubteamCreated(
        @JsonProperty("subteam") val userGroup: UserGroup) : EventData {
    companion object {
        /**
         * A User Group has been added to the workspace
         * https://api.slack.com/events/subteam_created
         */
        const val TYPE = "subteam_created"
    }
}

@JacksonDataClass
data class SubteamMembersChanged(
        @JsonProperty("subteam_id") val userGroupId: String,
        @InstantToInt @JsonProperty("date_previous_update") val previouslyModifiedAt: Instant,
        @InstantToInt @JsonProperty("date_update") val modifiedAt: Instant,
        @JsonProperty("added_users") val addedUserIds: Set<String>,
        @JsonProperty("added_users_count") val addedUserCount: String,
        @JsonProperty("removed_users") val removedUserIds: Set<String>,
        @JsonProperty("removed_users_count") val removedUserCount: String) : EventData {
    companion object {
        /**
         * The membership of an existing User Group has changed
         * https://api.slack.com/events/subteam_members_changed
         */
        const val TYPE = "subteam_members_changed"
    }
}
