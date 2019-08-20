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
        JsonSubTypes.Type(value = SuccessfulGroupsLeaveResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorGroupsLeaveResponse::class, name = "false")
)

@JacksonDataClass
sealed class GroupsLeaveResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * [SlackDoc](https://api.slack.com/methods/groups.leave)
 */
data class SuccessfulGroupsLeaveResponse(override val ok: Boolean) : GroupsLeaveResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/groups.leave)
 */
data class ErrorGroupsLeaveResponse constructor(
        override val ok: Boolean,
        @JsonProperty("error") val error: String
) : GroupsLeaveResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/groups.leave)
 */
data class GroupsLeaveRequest(@JsonProperty("channel") val channelId: String) {
    companion object
}
