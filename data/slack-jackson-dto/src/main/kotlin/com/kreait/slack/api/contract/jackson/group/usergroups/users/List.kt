package com.kreait.slack.api.contract.jackson.group.usergroups.users

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulUsergroupsUsersListResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorUsergroupsUsersListResponse::class, name = "false")
)

@JacksonDataClass
sealed class UsergroupsUsersListResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property users the list of users in that usergroup
 */
@JacksonDataClass
data class SuccessfulUsergroupsUsersListResponse constructor(override val ok: Boolean,
                                                             @JsonProperty("users") val users: List<String>)
    : UsergroupsUsersListResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorUsergroupsUsersListResponse constructor(override val ok: Boolean,
                                                        @JsonProperty("error") val error: String)
    : UsergroupsUsersListResponse(ok) {
    companion object
}

/**
 * requests all users of a usergroup
 *
 * @property usergroupId the id of the usergroup
 * @property includeDisabled determines if disabled users should be included in the response
 */
@JacksonDataClass
data class UsergroupsUsersListRequest(@JsonProperty("usergroup") val usergroupId: String,
                                      @JsonProperty("include_disabled") val includeDisabled: Boolean?) {
    companion object

    fun toRequestMap(): MutableMap<String, String> {
        val request = mutableMapOf<String, String>()
        usergroupId.let { request.put("usergroup", it) }
        includeDisabled?.let { request.put("include_disabled", it.toString()) }
        return request
    }
}
