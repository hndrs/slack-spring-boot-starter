package com.kreait.slack.api.contract.jackson.group.groups

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.JacksonDataClass

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulGroupsCreateResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorGroupsCreateResponse::class, name = "false")
)

@JacksonDataClass
sealed class GroupsCreateResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * [SlackDoc](https://api.slack.com/methods/groups.Create)
 */
data class SuccessfulGroupsCreateResponse(
        override val ok: Boolean) : GroupsCreateResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/groups.Create)
 */
data class ErrorGroupsCreateResponse constructor(
        override val ok: Boolean,
        @JsonProperty("error") val error: String
) : GroupsCreateResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/groups.Create)
 */
data class GroupsCreateRequest(@JsonProperty("channel") val channel: String) {
    companion object
}
