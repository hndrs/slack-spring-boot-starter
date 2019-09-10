package com.kreait.slack.api.contract.jackson.group.groups

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.common.types.Group
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulGroupsCreateChildResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorGroupsCreateChildResponse::class, name = "false")
)

@JacksonDataClass
sealed class GroupsCreateChildResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property group the created child-group object
 */
data class SuccessfulGroupsCreateChildResponse(
        override val ok: Boolean,
        @JsonProperty("group") val group: Group) : GroupsCreateChildResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
data class ErrorGroupsCreateChildResponse constructor(
        override val ok: Boolean,
        @JsonProperty("error") val error: String
) : GroupsCreateChildResponse(ok) {
    companion object
}

/**
 * This method takes an existing private channel and performs the following steps:
 * - Renames the existing private channel (from "example" to "example-archived").
 * - Archives the existing private channel.
 * - Creates a new private channel with the name of the existing private channel.
 * -  Adds all members of the existing private channel to the new private channel.
 *
 * @property channelId the channel-id of the group you want to re-create
 */
data class GroupsCreateChildRequest(@JsonProperty("channel") val channelId: String) {
    companion object
}
