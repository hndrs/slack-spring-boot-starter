package io.olaph.slack.dto.jackson.common.types

import com.fasterxml.jackson.annotation.JsonProperty
import io.olaph.slack.dto.jackson.JacksonDataClass

@JacksonDataClass
data class Member(
        @JsonProperty("id") val id: String,
        @JsonProperty("team_id") val teamId: String,
        @JsonProperty("name") val name: String,
        @JsonProperty("deleted") val deleted: Boolean,
        @JsonProperty("color") val color: String?,
        @JsonProperty("real_name") val realName: String?,
        @JsonProperty("tz") val tz: String?,
        @JsonProperty("tz_label") val tzLabel: String?,
        @JsonProperty("tz_offset") val tzOffset: Int?,
        @JsonProperty("profile") val profile: UserProfile,
        @JsonProperty("is_admin") val isAdmin: Boolean,
        @JsonProperty("is_owner") val isOwner: Boolean,
        @JsonProperty("is_primary_owner") val isPrimaryOwner: Boolean,
        @JsonProperty("is_restricted") val isRestricted: Boolean,
        @JsonProperty("is_ultra_restricted") val isUltraRestricted: Boolean,
        @JsonProperty("is_bot") val isBot: Boolean,
        @JsonProperty("updated") val updated: Int,
        @JsonProperty("is_app_user") val isAppUser: Boolean,
        @JsonProperty("has_2fa") val has2fa: Boolean,
        @JsonProperty("locale") val locale: String?
) {
    companion object
}