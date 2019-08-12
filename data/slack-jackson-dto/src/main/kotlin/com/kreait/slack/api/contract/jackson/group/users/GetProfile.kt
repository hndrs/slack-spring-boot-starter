package com.kreait.slack.api.contract.jackson.group.users

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.JacksonDataClass
import com.kreait.slack.api.contract.jackson.common.types.UserProfile

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulUsersGetProfileResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorUsersGetProfileResponse::class, name = "false")
)
@JacksonDataClass
sealed class UsersGetProfileResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulUsersGetProfileResponse constructor(override val ok: Boolean,
                                                         @JsonProperty("profile") val profile: UserProfile)
    : UsersGetProfileResponse(ok) {
    companion object {}
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
