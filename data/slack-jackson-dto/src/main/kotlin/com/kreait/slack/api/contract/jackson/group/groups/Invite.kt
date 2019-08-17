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
 * [SlackDoc](https://api.slack.com/methods/groups.invite)
 */
data class SuccessfulGroupsInviteResponse(override val ok: Boolean,
                                          @JsonProperty("group") val group: Group,
                                          @JsonProperty("already_in_group") val userAlreadyInGroup: Boolean?) : GroupsInviteResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/groups.invite)
 */
data class ErrorGroupsInviteResponse(override val ok: Boolean,
                                     @JsonProperty("error") val error: String) : GroupsInviteResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/groups.invite)
 */
data class GroupsInviteRequest(@JsonProperty("channel") val channelId: String,
                               @JsonProperty("user") val userId: String) {
    companion object
}
