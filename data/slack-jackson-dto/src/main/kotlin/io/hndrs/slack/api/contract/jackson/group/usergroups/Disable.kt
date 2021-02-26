package io.hndrs.slack.api.contract.jackson.group.usergroups

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.hndrs.slack.api.contract.jackson.util.JacksonDataClass

/**
 * https://api.slack.com/methods/usergroups.disable
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
    JsonSubTypes.Type(value = SuccessfulDisableResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorDisableResponse::class, name = "false")
)

@JacksonDataClass
sealed class DisableResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 */
@JacksonDataClass
data class SuccessfulDisableResponse constructor(
    override val ok: Boolean,
    @JsonProperty("usergroup") val userGroup: UserGroup
) : DisableResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorDisableResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : DisableResponse(ok) {
    companion object
}

/**
 * This method disables an existing User Group.
 *
 * @property userGroup The encoded ID of the User Group to disable.
 * @property includeCount Include the number of users in the User Group.
 */
@JacksonDataClass
data class DisableRequest constructor(
    @JsonProperty("usergroup") val userGroup: UserGroup,
    @JsonProperty("inclue_count") val includeCount: Boolean?
) {
    companion object
}

