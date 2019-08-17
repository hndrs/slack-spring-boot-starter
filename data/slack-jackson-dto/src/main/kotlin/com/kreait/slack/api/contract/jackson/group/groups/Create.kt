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
        JsonSubTypes.Type(value = SuccessfulGroupsCreateResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorGroupsCreateResponse::class, name = "false")
)

@JacksonDataClass
sealed class GroupsCreateResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * [SlackDoc](https://api.slack.com/methods/groups.Create)
 */
data class SuccessfulGroupsCreateResponse(
        override val ok: Boolean,
        @JsonProperty("group") val group: Group) : GroupsCreateResponse(ok) {
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
data class GroupsCreateRequest(@JsonProperty("name") val name: String,
                               @JsonProperty("validate") val validate: Boolean = true) {
    companion object
}