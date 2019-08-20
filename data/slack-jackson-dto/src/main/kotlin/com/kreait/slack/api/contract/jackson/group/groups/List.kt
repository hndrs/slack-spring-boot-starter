package com.kreait.slack.api.contract.jackson.group.groups

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.common.ResponseMetadata
import com.kreait.slack.api.contract.jackson.common.types.Group
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulGroupsListResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorGroupsListResponse::class, name = "false")
)

@JacksonDataClass
sealed class GroupsListResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * [SlackDoc](https://api.slack.com/methods/groups.list)
 */
data class SuccessfulGroupsListResponse(
        override val ok: Boolean,
        @JsonProperty("groups") val group: List<Group>,
        @JsonProperty("response_metadata") val responseMetadata: ResponseMetadata) : GroupsListResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/groups.list)
 */
data class ErrorGroupsListResponse constructor(
        override val ok: Boolean,
        @JsonProperty("error") val error: String
) : GroupsListResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/groups.list)
 */
data class GroupsListRequest(@JsonProperty("cursor") val cursor: String,
                             @JsonProperty("exclude_archived") val excludeArchived: Boolean? = null,
                             @JsonProperty("exclude_members") val excludeMembers: Boolean? = null,
                             @JsonProperty("limit") val limit: Int? = null) {
    companion object
}
