package com.kreait.slack.api.contract.jackson.event

import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
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
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        visible = true,
        defaultImpl = Event.Generic::class)
@JsonSubTypes(
        JsonSubTypes.Type(value = Event.TeamJoin::class, name = Event.TeamJoin.TYPE),
        JsonSubTypes.Type(value = Event.UserChange::class, name = Event.UserChange.TYPE),
        JsonSubTypes.Type(value = Event.SubteamMembersChanged::class, name = Event.SubteamMembersChanged.TYPE),
        JsonSubTypes.Type(value = Event.SubteamCreated::class, name = Event.SubteamCreated.TYPE)
)
@JacksonDataClass
abstract class Event constructor(@JsonProperty("type") open val type: String) {


    data class Generic(override val type: String) : Event(type) {
        val data: Map<String, Any>
            get() = internalData.toMap()

        /**
         * we use this internal data map to make use of the json any setter
         * which is not supported for use with [com.fasterxml.jackson.annotation.JsonCreator]
         * annotation yet
         */
        private val internalData: MutableMap<String, Any> = mutableMapOf()

        /**
         * this method can only be used once to ensure immutablity
         * It is a workaround
         */
        @JsonAnySetter
        internal fun anySetter(key: String, value: Any) {
            this.internalData[key] = value
        }

        override fun toString(): String {
            return "Generic(type='$type', data=$internalData)"
        }


    }

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
    }
}
