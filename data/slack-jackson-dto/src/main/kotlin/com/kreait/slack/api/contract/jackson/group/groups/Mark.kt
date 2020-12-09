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
    JsonSubTypes.Type(value = SuccessfulGroupsMarkResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorGroupsMarkResponse::class, name = "false")
)

@JacksonDataClass
sealed class GroupsMarkResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 */
data class SuccessfulGroupsMarkResponse(override val ok: Boolean) : GroupsMarkResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
data class ErrorGroupsMarkResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : GroupsMarkResponse(ok) {
    companion object
}

/**
 * Sets the read cursor in a private channel.
 *
 * @property channelId the channel-id of the channel you want to mark
 * @property timestamp the timestamp of the message, where the read cursor should point to
 */
data class GroupsMarkRequest(
    @JsonProperty("channel") val channelId: String,
    @JsonProperty("ts") val timestamp: Boolean = true
) {
    companion object
}
