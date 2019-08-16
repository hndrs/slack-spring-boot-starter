package com.kreait.slack.api.contract.jackson.group.groups

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.JacksonDataClass
import com.kreait.slack.api.contract.jackson.common.types.Group

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
 * [SlackDoc](https://api.slack.com/methods/groups.CreateChild)
 */
data class SuccessfulGroupsCreateChildResponse(
        override val ok: Boolean,
        @JsonProperty("group") val group: Group) : GroupsCreateChildResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/groups.CreateChild)
 */
data class ErrorGroupsCreateChildResponse constructor(
        override val ok: Boolean,
        @JsonProperty("error") val error: String
) : GroupsCreateChildResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/groups.CreateChild)
 */
data class GroupsCreateChildRequest(@JsonProperty("channel") val channel: String) {
    companion object
}
