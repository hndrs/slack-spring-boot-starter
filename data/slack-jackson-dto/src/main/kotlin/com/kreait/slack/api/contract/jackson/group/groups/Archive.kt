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
        JsonSubTypes.Type(value = SuccessfulGroupsArchiveResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorGroupsArchiveResponse::class, name = "false")
)
@JacksonDataClass
sealed class GroupsArchiveResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 */
data class SuccessfulGroupsArchiveResponse(
        override val ok: Boolean) : GroupsArchiveResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
data class ErrorGroupsArchiveResponse constructor(
        override val ok: Boolean,
        @JsonProperty("error") val error: String
) : GroupsArchiveResponse(ok) {
    companion object
}

/**
 * Archives a group
 *
 * @property channelId the channel-id of the group you want to archive
 */
data class GroupsArchiveRequest(@JsonProperty("channel") val channelId: String) {
    companion object
}
