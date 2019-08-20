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
        JsonSubTypes.Type(value = SuccessfulGroupsMarkResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorGroupsMarkResponse::class, name = "false")
)

@JacksonDataClass
sealed class GroupsMarkResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * [SlackDoc](https://api.slack.com/methods/groups.mark)
 */
data class SuccessfulGroupsMarkResponse(override val ok: Boolean) : GroupsMarkResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/groups.mark)
 */
data class ErrorGroupsMarkResponse constructor(
        override val ok: Boolean,
        @JsonProperty("error") val error: String
) : GroupsMarkResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/groups.mark)
 */
data class GroupsMarkRequest(@JsonProperty("channel") val channelId: String,
                             @JsonProperty("ts") val timestamp: Boolean = true) {
    companion object
}
