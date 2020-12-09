package com.kreait.slack.api.contract.jackson.group.groups

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.common.types.Group
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "ok",
    visible = true
)
@JsonSubTypes(
    JsonSubTypes.Type(value = SuccessfulGroupsCreateResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorGroupsCreateResponse::class, name = "false")
)

@JacksonDataClass
sealed class GroupsCreateResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property group the created group object
 */
data class SuccessfulGroupsCreateResponse(
    override val ok: Boolean,
    @JsonProperty("group") val group: Group
) : GroupsCreateResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
data class ErrorGroupsCreateResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : GroupsCreateResponse(ok) {
    companion object
}

/**
 * Creates a group
 *
 * @property name the name of the group you want to create
 * @property validate Whether to return errors on invalid channel name instead of modifying it to meet the specified criteria.
 */
data class GroupsCreateRequest(
    @JsonProperty("name") val name: String,
    @JsonProperty("validate") val validate: Boolean = true
) {
    companion object
}
