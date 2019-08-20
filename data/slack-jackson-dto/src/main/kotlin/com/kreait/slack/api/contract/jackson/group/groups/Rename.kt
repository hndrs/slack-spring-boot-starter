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
        JsonSubTypes.Type(value = SuccessfulGroupsRenameResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorGroupsRenameResponse::class, name = "false")
)

@JacksonDataClass
sealed class GroupsRenameResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * [SlackDoc](https://api.slack.com/methods/groups.rename)
 */
data class SuccessfulGroupsRenameResponse(
        override val ok: Boolean,
        @JsonProperty("channel") val group: Group) : GroupsRenameResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/groups.rename)
 */
data class ErrorGroupsRenameResponse constructor(
        override val ok: Boolean,
        @JsonProperty("error") val error: String
) : GroupsRenameResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/groups.rename)
 */
data class GroupsRenameRequest(@JsonProperty("channel") val channelId: String,
                               @JsonProperty("name") val newName: String,
                               @JsonProperty("validate") val validate: Boolean? = null) {
    companion object
}
