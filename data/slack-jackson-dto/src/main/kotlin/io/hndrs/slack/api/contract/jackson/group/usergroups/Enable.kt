package io.hndrs.slack.api.contract.jackson.group.usergroups

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.hndrs.slack.api.contract.jackson.util.JacksonDataClass

/**
 * https://api.slack.com/methods/usergroups.enable
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
    JsonSubTypes.Type(value = SuccessfulEnableResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorEnableResponse::class, name = "false")
)

@JacksonDataClass
sealed class EnableResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 */
@JacksonDataClass
data class SuccessfulEnableResponse constructor(
    override val ok: Boolean,
    @JsonProperty("usergroup") val userGroup: UserGroup
) : EnableResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorEnableResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : EnableResponse(ok) {
    companion object
}

/**
 * Enable a User Group
 *
 * @property usergroupId The encoded ID of the User Group to enable.
 * @property includeCount Include the number of users in the User Group.
 */
@JacksonDataClass
data class EnableRequest constructor(
    @JsonProperty("usergroup") val usergroupId: String,
    @JsonProperty("include_count") val includeCount: Boolean?
) {
    companion object
}
