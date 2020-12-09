package com.kreait.slack.api.contract.jackson.group.groups

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.common.ResponseMetadata
import com.kreait.slack.api.contract.jackson.common.types.Group
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "ok",
    visible = true
)
@JsonSubTypes(
    JsonSubTypes.Type(value = SuccessfulGroupsListResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorGroupsListResponse::class, name = "false")
)

@JacksonDataClass
sealed class GroupsListResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property group list of available groups
 * @property responseMetadata metadata used for paging
 */
data class SuccessfulGroupsListResponse(
    override val ok: Boolean,
    @JsonProperty("groups") val group: List<Group>,
    @JsonProperty("response_metadata") val responseMetadata: ResponseMetadata
) : GroupsListResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
data class ErrorGroupsListResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : GroupsListResponse(ok) {
    companion object
}

/**
 * Lists private channels that the calling user has access to.
 *
 * @property cursor Parameter for pagination. Set cursor equal to the next_cursor attribute returned by the previous request's response_metadata. This parameter is optional, but pagination is mandatory: the default value simply fetches the first "page" of the collection.
 * @property excludeArchived determines if archived groups should be excluded
 * @property excludeMembers determines if members should be excluded
 * @property limit the max-count of groups you want to request
 */
data class GroupsListRequest(
    @JsonProperty("cursor") val cursor: String,
    @JsonProperty("exclude_archived") val excludeArchived: Boolean? = null,
    @JsonProperty("exclude_members") val excludeMembers: Boolean? = null,
    @JsonProperty("limit") val limit: Int? = null
) {
    companion object
}
