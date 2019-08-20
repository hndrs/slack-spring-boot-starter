package com.kreait.slack.api.contract.jackson.group.groups

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulGroupsUnarchiveResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorGroupsUnarchiveResponse::class, name = "false")
)
@JacksonDataClass
sealed class GroupsUnarchiveResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * [SlackDoc](https://api.slack.com/methods/groups.unarchive)
 */
data class SuccessfulGroupsUnarchiveResponse(
        override val ok: Boolean) : GroupsUnarchiveResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/groups.unarchive)
 */
data class ErrorGroupsUnarchiveResponse constructor(
        override val ok: Boolean,
        @JsonProperty("error") val error: String
) : GroupsUnarchiveResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/groups.unarchive)
 */
data class GroupsUnarchiveRequest(@JsonProperty("channel") val channelId: String) {
    companion object
}
