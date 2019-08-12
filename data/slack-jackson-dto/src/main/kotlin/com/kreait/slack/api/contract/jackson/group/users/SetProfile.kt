package com.kreait.slack.api.contract.jackson.group.users

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.JacksonDataClass

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulSetProfileResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorSetProfileResponse::class, name = "false")
)
@JacksonDataClass
sealed class SetProfileResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulSetProfileResponse constructor(override val ok: Boolean,
                                                    @JsonProperty("user") val user: Profile) : SetProfileResponse(ok) {
    companion object {}

    data class Profile(
            @JsonProperty("avatar_hash") val avatarHash: String?,
            @JsonProperty("display_name") val displayName: String?,
            @JsonProperty("display_name_normalized") val displayNameNormalized: String,
            @JsonProperty("email") val email: String?,
            @JsonProperty("fields") val fields: Map<String, String>?,
            @JsonProperty("first_name") val firstName: String?,
            @JsonProperty("image_192") val image192: String?,
            @JsonProperty("image_24") val image24: String?,
            @JsonProperty("image_32") val image32: String?,
            @JsonProperty("image_48") val image48: String?,
            @JsonProperty("image_512") val image512: String?,
            @JsonProperty("image_72") val image72: String?,
            @JsonProperty("last_name") val lastName: String?,
            @JsonProperty("phone") val phone: String?,
            @JsonProperty("real_name") val realName: String?,
            @JsonProperty("real_name_normalized") val realNameNormalized: String?,
            @JsonProperty("skype") val skype: String?,
            @JsonProperty("status_emoji") val statusEmoji: String?,
            @JsonProperty("status_expiration") val statusExpiration: Int?,
            @JsonProperty("status_text") val statusText: String?,
            @JsonProperty("status_text_canonical") val statusTextCanonical: String?,
            @JsonProperty("title") val title: String?) {
        companion object
    }
}

@JacksonDataClass
data class ErrorSetProfileResponse constructor(override val ok: Boolean,
                                               @JsonProperty("error") val error: String)
    : SetProfileResponse(ok) {
    companion object
}

//TODO Unused?
@JacksonDataClass
data class SetProfileRequest(@JsonProperty("name") val name: String?,
                             @JsonProperty("profile") val profile: Map<String, String>?,
                             @JsonProperty("user") val user: String?,
                             @JsonProperty("value") val value: String?) {
    companion object
}
