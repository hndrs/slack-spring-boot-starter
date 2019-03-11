package io.olaph.slack.dto.jackson.group.users

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.olaph.slack.dto.jackson.JacksonDataClass


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulUserListResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorUserListResponse::class, name = "false")
)
@JacksonDataClass
abstract class UserListResponse(
        @JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulUserListResponse(
        override val ok: Boolean,
        @JsonProperty("members") val members: List<Member>,
        @JsonProperty("cache_ts") val cacheTs: Int
) : UserListResponse(ok)

@JacksonDataClass
data class ErrorUserListResponse constructor(override val ok: Boolean,
                                             @JsonProperty("error") val error: String)
    : UserListResponse(ok)

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
        @JsonProperty("has_2fa") val has2fa: Boolean
)

@JacksonDataClass
data class UserProfile(
        @JsonProperty("title") val title: String,
        @JsonProperty("phone") val phone: String?,
        @JsonProperty("skype") val skype: String?,
        @JsonProperty("real_name") val realName: String?,
        @JsonProperty("real_name_normalized") val realNameNormalized: String?,
        @JsonProperty("display_name") val displayName: String,
        @JsonProperty("display_name_normalized") val displayNameNormalized: String,
        @JsonProperty("fields") val fields: String?,
        @JsonProperty("status_text") val statusText: String,
        @JsonProperty("status_emoji") val statusEmoji: String,
        @JsonProperty("status_expiration") val statusExpiration: Int,
        @JsonProperty("avatar_hash") val avatarHash: String?,
        @JsonProperty("always_active") val alwaysActive: Boolean,
        @JsonProperty("image_original") val image_original: String?,
        @JsonProperty("email") val email: String?,
        @JsonProperty("first_name") val firstName: String?,
        @JsonProperty("last_name") val lastName: String?,
        @JsonProperty("image_24") val image24: String?,
        @JsonProperty("image_32") val image32: String?,
        @JsonProperty("image_48") val image48: String?,
        @JsonProperty("image_72") val image72: String?,
        @JsonProperty("image_192") val image192: String?,
        @JsonProperty("image_512") val image512: String?,
        @JsonProperty("image_1024") val image_1024: String?,
        @JsonProperty("status_text_canonical") val statusTextCanonical: String,
        @JsonProperty("team") val team: String
)
