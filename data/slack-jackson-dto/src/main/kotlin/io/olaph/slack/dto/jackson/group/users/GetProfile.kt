package io.olaph.slack.dto.jackson.group.users

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.olaph.slack.dto.jackson.JacksonDataClass

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulUsersGetProfileResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorUsersGetProfileResponse::class, name = "false")
)
@JacksonDataClass
sealed class UsersGetProfileResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulUsersGetProfileResponse constructor(override val ok: Boolean,
                                                         @JsonProperty("profile") val profile: Profile)
    : UsersGetProfileResponse(ok) {
    companion object {}


    @JacksonDataClass
    data class Profile(
            @JsonProperty("title") val title: String?,
            @JsonProperty("phone") val phone: String?,
            @JsonProperty("skype") val skype: String?,
            @JsonProperty("real_name") val realName: String?,
            @JsonProperty("real_name_normalized") val realNameNormalized: String?,
            @JsonProperty("display_name") val displayName: String?,
            @JsonProperty("display_name_normalized") val displayNameNormalized: String?,
            @JsonProperty("fields") val fields: Any?,
            @JsonProperty("status_text") val statusText: String?,
            @JsonProperty("status_emoji") val statusEmoji: String?,
            @JsonProperty("status_expiration") val statusExpiration: Int?,
            @JsonProperty("avatar_hash") val avatarHash: String?,
            @JsonProperty("email") val email: String?,
            @JsonProperty("image_24") val image24: String?,
            @JsonProperty("image_32") val image32: String?,
            @JsonProperty("image_48") val image48: String?,
            @JsonProperty("image_72") val image72: String?,
            @JsonProperty("image_192") val image192: String?,
            @JsonProperty("image_512") val image512: String?,
            @JsonProperty("status_text_canonical") val statusTextCanonical: String?,
            @JsonProperty("team") val team: String?
    ) {
        companion object
    }
}

@JacksonDataClass
data class ErrorUsersGetProfileResponse constructor(override val ok: Boolean,
                                                    @JsonProperty("error") val error: String)
    : UsersGetProfileResponse(ok) {
    companion object
}

data class UsersGetProfileRequest(private val include_labels: Boolean? = false, private val user: String? = "") {

    companion object {}

    fun toRequestMap(): MutableMap<String, String> {
        val requestMap = mutableMapOf<String, String>()
        include_labels?.let { requestMap.put("include_labels", it.toString()) }
        user?.let { requestMap.put("user", it) }
        return requestMap
    }
}
