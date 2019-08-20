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
        JsonSubTypes.Type(value = SuccessfulGroupsKickResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorGroupsKickResponse::class, name = "false")
)

@JacksonDataClass
sealed class GroupsKickResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * [SlackDoc](https://api.slack.com/methods/groups.kick)
 */
data class SuccessfulGroupsKickResponse(override val ok: Boolean) : GroupsKickResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/groups.kick)
 */
data class ErrorGroupsKickResponse constructor(
        override val ok: Boolean,
        @JsonProperty("error") val error: String
) : GroupsKickResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/groups.kick)
 */
data class GroupsKickRequest(@JsonProperty("channel") val channelId: String,
                             @JsonProperty("user") val userId: Boolean = true) {
    companion object
}
