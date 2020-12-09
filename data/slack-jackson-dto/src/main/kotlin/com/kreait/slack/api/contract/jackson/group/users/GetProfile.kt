package com.kreait.slack.api.contract.jackson.group.users

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.common.types.UserProfile
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
    JsonSubTypes.Type(value = SuccessfulGetProfileResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorGetProfileResponse::class, name = "false")
)
@JacksonDataClass
sealed class GetProfileResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property profile the profile of the user
 */
data class SuccessfulGetProfileResponse constructor(
    override val ok: Boolean,
    @JsonProperty("profile") val profile: UserProfile
) : GetProfileResponse(ok) {
    companion object {}
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorGetProfileResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : GetProfileResponse(ok) {
    companion object
}

/**
 * Retrieves a user's profile information.
 *
 * @property includeLabels true if labels should be included
 * @property user the user id of the user you want to request the profile from
 */
data class GetProfileRequest(private val includeLabels: Boolean? = false, private val user: String? = "") {

    fun toRequestMap(): MutableMap<String, String> {
        val requestMap = mutableMapOf<String, String>()
        includeLabels?.let { requestMap.put("include_labels", it.toString()) }
        user?.let { requestMap.put("user", it) }
        return requestMap
    }

    companion object
}
