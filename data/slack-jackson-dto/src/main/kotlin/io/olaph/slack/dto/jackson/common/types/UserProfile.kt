package io.olaph.slack.dto.jackson.common.types

import com.fasterxml.jackson.annotation.JsonProperty
import io.olaph.slack.dto.jackson.JacksonDataClass

@JacksonDataClass
data class UserProfile(
        @JsonProperty("title") val title: String?,
        @JsonProperty("phone") val phone: String?,
        @JsonProperty("skype") val skype: String?,
        @JsonProperty("real_name") val realName: String?,
        @JsonProperty("real_name_normalized") val realNameNormalized: String?,
        @JsonProperty("display_name") val displayName: String,
        @JsonProperty("display_name_normalized") val displayNameNormalized: String,
        @JsonProperty("fields") val fields: Map<String, String>?,
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
) {
    companion object
}