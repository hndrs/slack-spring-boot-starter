package com.kreait.slack.api.contract.jackson.common.types

import com.fasterxml.jackson.annotation.JsonProperty
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

/**
 * The Profile of a User
 *
 * @property title title of a user
 * @property phone phone number
 * @property skype skype address
 * @property realName the real name with escaped special characters
 * @property realNameNormalized the real name with normalized characters
 * @property displayName the display name with escaped special characters
 * @property displayNameNormalized the display name with normalized characters
 * @property fields
 * @property statusText the status text
 * @property statusEmoji the status emoji
 * @property statusExpiration the expiration date of the status
 * @property avatarHash the hash of the avatar
 * @property alwaysActive
 * @property image_original url to original image
 * @property email email address of the user
 * @property firstName  first name of the user
 * @property lastName last name of the user
 * @property image24 url to scaled image
 * @property image32 url to scaled image
 * @property image48 url to scaled image
 * @property image72 url to scaled image
 * @property image192 url to scaled image
 * @property image512 url to scaled image
 * @property image_1024 url to scaled image
 * @property statusTextCanonical
 * @property team the team-id of the user
 */
@JacksonDataClass
data class UserProfile(
        @JsonProperty("avatar_hash")
        val avatarHash: String? = null,
        @JsonProperty("display_name")
        val displayName: String,
        @JsonProperty("display_name_normalized")
        val displayNameNormalized: String,
        @JsonProperty("email")
        val email: String? = null,
        @JsonProperty("fields")
        val fields: Map<Any, Field>?,
        @JsonProperty("first_name")
        val firstName: String? = null,
        @JsonProperty("image_1024")
        val image1024: String? = null,
        @JsonProperty("image_192")
        val image192: String? = null,
        @JsonProperty("image_24")
        val image24: String? = null,
        @JsonProperty("image_32")
        val image32: String? = null,
        @JsonProperty("image_48")
        val image48: String? = null,
        @JsonProperty("image_512")
        val image512: String? = null,
        @JsonProperty("image_72")
        val image72: String? = null,
        @JsonProperty("image_original")
        val imageOriginal: String? = null,
        @JsonProperty("is_custom_image")
        val isCustomImage: Boolean,
        @JsonProperty("last_name")
        val lastName: String? = null,
        @JsonProperty("phone")
        val phone: String? = null,
        @JsonProperty("real_name")
        val realName: String? = null,
        @JsonProperty("real_name_normalized")
        val realNameNormalized: String? = null,
        @JsonProperty("skype")
        val skype: String? = null,
        @JsonProperty("status_emoji")
        val statusEmoji: String,
        @JsonProperty("status_expiration")
        val statusExpiration: Int,
        @JsonProperty("status_text")
        val statusText: String,
        @JsonProperty("status_text_canonical")
        val statusTextCanonical: String,
        @JsonProperty("team")
        val team: String,
        @JsonProperty("title")
        val title: String? = null,
        @JsonProperty("always_active")
        val alwaysActive: Boolean = false) {

    data class Field(@JsonProperty("value")
                     val value: String,
                     @JsonProperty("alt")
                     val alt: String)

    companion object {}
}