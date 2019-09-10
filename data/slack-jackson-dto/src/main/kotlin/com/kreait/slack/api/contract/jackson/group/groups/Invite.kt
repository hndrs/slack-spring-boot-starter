package com.kreait.slack.api.contract.jackson.group.groups

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.common.types.Group
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

/**
 * [SlackDoc](https://api.slack.com/methods/groups.invite)
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulGroupsInviteResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorGroupsInviteResponse::class, name = "false")
)
@JacksonDataClass
sealed class GroupsInviteResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property group the new group object with the invited user
 * @property userAlreadyInGroup determines if the user is already in the group
 */
data class SuccessfulGroupsInviteResponse(override val ok: Boolean,
                                          @JsonProperty("group") val group: Group,
                                          @JsonProperty("already_in_group") val userAlreadyInGroup: Boolean?) : GroupsInviteResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
data class ErrorGroupsInviteResponse(override val ok: Boolean,
                                     @JsonProperty("error") val error: String) : GroupsInviteResponse(ok) {
    companion object
}

/**
 * Adds a user to a group
 *
 * @property channelId the channel id of the channel to which you want to add the user
 * @property userId the user you want to add
 */
data class GroupsInviteRequest(@JsonProperty("channel") val channelId: String,
                               @JsonProperty("user") val userId: String) {
    companion object
}
