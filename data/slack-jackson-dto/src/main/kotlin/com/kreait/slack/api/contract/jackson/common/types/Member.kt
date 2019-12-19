package com.kreait.slack.api.contract.jackson.common.types

import com.fasterxml.jackson.annotation.JsonProperty
import com.kreait.slack.api.contract.jackson.util.InstantToInt
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import java.time.Instant

/**
 * A data-class representing a Member.
 *
 * @property id Indicates the unique id of the member.
 * @property teamId Indicates the team id the member is part of.
 * @property name Indicates the member's name.
 * @property deleted States if the member is deleted.
 * @property color Used to display color in some clients.
 * @property realName Indicates the member's real name.
 * @property timezone Indicates the timezone the member is currently in (e.g. "America/Los_Angeles").
 * @property timezoneLabel A String describing the name of the timezone (e.g. "Pacific Standard Time").
 * @property timezoneOffset A signed Integer indiciating the number of seconds to offset UTC time by.
 * @property profile Holds the [UserProfile] of the member.
 * @property isAdmin Indicates if the member is admin in the workspace.
 * @property isOwner Indicates if the member is owner of the workspace.
 * @property isPrimaryOwner Indicates if the member is the primary owner of the workspace. Primary ownership can be transfered to other members.
 * @property isRestricted Indicates if the member is a multi-channel guest.
 * @property isUltraRestricted Indicates that the member is a single channel guest.
 * @property isBot Indicates if the member is a bot.
 * @property lastModifiedAt Indicates when the member was last updated.
 * @property isAppUser Indicates if the member is currently using the app.
 * @property has2fa Indicates if the member has two-factor authentication enabled.
 * @property locale Indicates the locale the member is using.
 *
 * @see [UserProfile]
 */
@JacksonDataClass
data class Member(
        @JsonProperty("color")
        val color: String? = null,
        @JsonProperty("deleted")
        val isDeleted: Boolean,
        @JsonProperty("id")
        val id: String,
        @JsonProperty("is_admin")
        val isAdmin: Boolean,
        @JsonProperty("is_app_user")
        val isAppUser: Boolean,
        @JsonProperty("is_bot")
        val isBot: Boolean,
        @JsonProperty("is_owner")
        val isOwner: Boolean,
        @JsonProperty("is_primary_owner")
        val isPrimaryOwner: Boolean,
        @JsonProperty("is_restricted")
        val isRestricted: Boolean,
        @JsonProperty("is_ultra_restricted")
        val isUltraRestricted: Boolean,
        @JsonProperty("locale")
        val locale: String? = null,
        @JsonProperty("name")
        val name: String,
        @JsonProperty("profile")
        val profile: UserProfile,
        @JsonProperty("real_name")
        val realName: String? = null,
        @JsonProperty("team_id")
        val teamId: String,
        @JsonProperty("tz")
        val timezone: String? = null,
        @JsonProperty("tz_label")
        val timezoneLabel: String? = null,
        @JsonProperty("tz_offset")
        val timezoneOffset: Int?,
        @InstantToInt @JsonProperty("updated")
        val updated: Instant? = null,
        @JsonProperty("has_2fa")
        val has2fa: Boolean,
        @InstantToInt @JsonProperty("last_modified_at")
        val lastModifiedAt: Instant? = null
) {
    companion object {}
}
