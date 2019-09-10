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
 * Success-response of this request.
 *
 * @property ok will be true
 */
data class SuccessfulGroupsKickResponse(override val ok: Boolean) : GroupsKickResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
data class ErrorGroupsKickResponse constructor(
        override val ok: Boolean,
        @JsonProperty("error") val error: String
) : GroupsKickResponse(ok) {
    companion object
}

/**
 * Removes a user from a group
 *
 * @property channelId the channel id of the channel you want to remove the user from
 * @property userId the user id you want to remove
 */
data class GroupsKickRequest(@JsonProperty("channel") val channelId: String,
                             @JsonProperty("user") val userId: Boolean = true) {
    companion object
}
