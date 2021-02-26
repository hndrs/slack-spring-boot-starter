package io.hndrs.slack.api.contract.jackson.group.usergroups.users

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.hndrs.slack.api.contract.jackson.group.usergroups.UserGroup
import io.hndrs.slack.api.contract.jackson.util.JacksonDataClass

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
    JsonSubTypes.Type(value = SuccessfulUsergroupUsersUpdateResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorUsergroupUsersUpdateResponse::class, name = "false")
)

@JacksonDataClass
sealed class UsergroupsUsersUpdateResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property userGroup the updated usergroup
 */
@JacksonDataClass
data class SuccessfulUsergroupUsersUpdateResponse constructor(
    override val ok: Boolean,
    @JsonProperty("usergroup") val userGroup: UserGroup
) :
    UsergroupsUsersUpdateResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorUsergroupUsersUpdateResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : UsergroupsUsersUpdateResponse(ok) {
    companion object
}

/**
 * Update the list of users for a User Group
 *
 * @property usergroup the id of the usergroup you want to update
 * @property users the new list of users
 * @property includeCount determines if the response should include the amount
 */
@JacksonDataClass
data class UsergroupUsersUpdateRequest(
    @JsonProperty("usergroup") val usergroup: String,
    @JsonProperty("users") val users: List<String>,
    @JsonProperty("include_count") val includeCount: Int?
) {
    companion object
}
