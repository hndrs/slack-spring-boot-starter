package com.kreait.slack.api.contract.jackson.group.users

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import com.kreait.slack.api.contract.jackson.common.types.UserProfile

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulGetProfileResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorGetProfileResponse::class, name = "false")
)
@JacksonDataClass
sealed class GetProfileResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulGetProfileResponse constructor(override val ok: Boolean,
                                                    @JsonProperty("profile") val profile: UserProfile)
    : GetProfileResponse(ok) {
    companion object {}
}

@JacksonDataClass
data class ErrorGetProfileResponse constructor(override val ok: Boolean,
                                               @JsonProperty("error") val error: String)
    : GetProfileResponse(ok) {
    companion object
}

data class GetProfileRequest(private val include_labels: Boolean? = false, private val user: String? = "") {

    companion object {}

    fun toRequestMap(): MutableMap<String, String> {
        val requestMap = mutableMapOf<String, String>()
        include_labels?.let { requestMap.put("include_labels", it.toString()) }
        user?.let { requestMap.put("user", it) }
        return requestMap
    }
}
