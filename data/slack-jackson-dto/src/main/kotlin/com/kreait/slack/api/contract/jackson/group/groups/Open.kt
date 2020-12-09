package com.kreait.slack.api.contract.jackson.group.groups

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "ok",
    visible = true
)
@JsonSubTypes(
    JsonSubTypes.Type(value = SuccessfulGroupsOpenResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorGroupsOpenResponse::class, name = "false")
)

@JacksonDataClass
sealed class GroupsOpenResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property noOp true if the channel is already open
 * @property isAlreadyOpen true if the channel is already open
 */
data class SuccessfulGroupsOpenResponse(
    override val ok: Boolean,
    @JsonProperty("no_op") val noOp: Boolean? = null,
    @JsonProperty("already_open") val isAlreadyOpen: Boolean? = null
) : GroupsOpenResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
data class ErrorGroupsOpenResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : GroupsOpenResponse(ok) {
    companion object
}

/**
 * Opens a private channel.
 *
 * @property channelId the channel-id of the group you want to open
 */
data class GroupsOpenRequest(@JsonProperty("channel") val channelId: String) {
    companion object
}
