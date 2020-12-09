package com.kreait.slack.api.contract.jackson.event.type.subteam

import com.fasterxml.jackson.annotation.JsonProperty
import com.kreait.slack.api.contract.jackson.event.Event
import com.kreait.slack.api.contract.jackson.util.InstantToInt
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import java.time.Instant

@JacksonDataClass
data class SubteamMembersChanged(
    override val type: String,
    @JsonProperty("subteam_id") val userGroupId: String,
    @InstantToInt @JsonProperty("date_previous_update") val previouslyModifiedAt: Instant,
    @InstantToInt @JsonProperty("date_update") val modifiedAt: Instant,
    @JsonProperty("added_users") val addedUserIds: Set<String>,
    @JsonProperty("added_users_count") val addedUserCount: String,
    @JsonProperty("removed_users") val removedUserIds: Set<String>,
    @JsonProperty("removed_users_count") val removedUserCount: String
) : Event(type) {

    override fun slackTypeString() = TYPE

    companion object {
        /**
         * The membership of an existing User Group has changed
         * https://api.slack.com/events/subteam_members_changed
         */
        const val TYPE = "subteam_members_changed"
    }
}
