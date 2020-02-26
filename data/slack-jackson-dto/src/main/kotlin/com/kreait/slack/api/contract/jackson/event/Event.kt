package com.kreait.slack.api.contract.jackson.event

import com.fasterxml.jackson.annotation.JsonProperty
import com.kreait.slack.api.contract.jackson.common.types.Member
import com.kreait.slack.api.contract.jackson.group.usergroups.UserGroup
import com.kreait.slack.api.contract.jackson.util.InstantToInt
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import java.time.Instant

/**
 * An event that triggered
 * @property type the type of the event
 * @property token the token that can be used for further actions
 */
@JacksonDataClass
abstract class Event constructor(@JsonProperty("type") open val type: String) {

    /**
     * returns the type that is specified by slack
     * https://api.slack.com/events
     */
    abstract fun slackTypeString(): String

    @JacksonDataClass
    data class TeamJoin(override val type: String,
                        @JsonProperty("user") val member: Member) : Event(type) {

        companion object {
            /**
             * A new member has joined
             * https://api.slack.com/events/team_join
             */
            const val TYPE = "team_join"
        }

        override fun slackTypeString() = TYPE
    }

    @JacksonDataClass
    data class UserChange(override val type: String,
                          @JsonProperty("user") val member: Member) : Event(type) {
        companion object {
            /**
             * A member's data has changed
             * https://api.slack.com/events/user_change
             */
            const val TYPE = "user_change"
        }

        override fun slackTypeString() = TYPE
    }

    @JacksonDataClass
    data class SubteamCreated(override val type: String,
                              @JsonProperty("subteam") val userGroup: UserGroup) : Event(type) {
        companion object {
            /**
             * A User Group has been added to the workspace
             * https://api.slack.com/events/subteam_created
             */
            const val TYPE = "subteam_created"
        }

        override fun slackTypeString() = TYPE
    }

    @JacksonDataClass
    data class SubteamMembersChanged(override val type: String,
                                     @JsonProperty("subteam_id") val userGroupId: String,
                                     @InstantToInt @JsonProperty("date_previous_update") val previouslyModifiedAt: Instant,
                                     @InstantToInt @JsonProperty("date_update") val modifiedAt: Instant,
                                     @JsonProperty("added_users") val addedUserIds: Set<String>,
                                     @JsonProperty("added_users_count") val addedUserCount: String,
                                     @JsonProperty("removed_users") val removedUserIds: Set<String>,
                                     @JsonProperty("removed_users_count") val removedUserCount: String) : Event(type) {
        companion object {
            /**
             * The membership of an existing User Group has changed
             * https://api.slack.com/events/subteam_members_changed
             */
            const val TYPE = "subteam_members_changed"
        }

        override fun slackTypeString() = TYPE
    }
}
