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
    JsonSubTypes.Type(value = SuccessfulGroupsRenameResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorGroupsRenameResponse::class, name = "false")
)

@JacksonDataClass
sealed class GroupsRenameResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property group the renamed group object
 */
data class SuccessfulGroupsRenameResponse(
    override val ok: Boolean,
    @JsonProperty("channel") val group: Group
) : GroupsRenameResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
data class ErrorGroupsRenameResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : GroupsRenameResponse(ok) {
    companion object
}

/**
 * Renames a group
 *
 * @property channelId the channel-id of the group you want to rename
 * @property newName the new name for the group
 * @property validate Whether to return errors on invalid channel name instead of modifying it to meet the specified criteria.
 */
data class GroupsRenameRequest(
    @JsonProperty("channel") val channelId: String,
    @JsonProperty("name") val newName: String,
    @JsonProperty("validate") val validate: Boolean? = null
) {
    companion object
}
