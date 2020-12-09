package com.kreait.slack.api.contract.jackson.group.usergroups

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.common.types.Channel
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

/**
 * https://api.slack.com/methods/usergroups.update
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
    JsonSubTypes.Type(value = SuccessfulUpdateResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorUpdateResponse::class, name = "false")
)

@JacksonDataClass
sealed class UpdateResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 */
@JacksonDataClass
data class SuccessfulUpdateResponse constructor(
    override val ok: Boolean,
    @JsonProperty("usergroup") val userGroup: UserGroup
) : UpdateResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorUpdateResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : UpdateResponse(ok) {
    companion object
}

/**
 * Update an existing User Group
 *
 * @property usergroupId The encoded ID of the User Group to update.
 * @property channels A comma separated string of encoded channel IDs for which the User Group uses as a default.
 * @property description A short description of the User Group.
 * @property handle A mention handle. Must be unique among channels, users and User Groups.
 * @property includeCount Include the number of users in the User Group.
 * @property name A name for the User Group. Must be unique among User Groups.
 */
@JacksonDataClass
data class UpdateRequest constructor(
    @JsonProperty("usergroup") val usergroupId: String,
    @JsonProperty("channels") val channels: List<Channel>?,
    @JsonProperty("description") val description: String?,
    @JsonProperty("handle") val handle: String?,
    @JsonProperty("include_count") val includeCount: Boolean?,
    @JsonProperty("name") val name: String?
) {
    companion object
}
