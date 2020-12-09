package com.kreait.slack.api.contract.jackson.group.channels

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.common.types.Channel
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "ok",
    visible = true
)

@JsonSubTypes(
    JsonSubTypes.Type(value = SuccessfulChannelInviteResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorChannelInviteResponse::class, name = "false")
)

@JacksonDataClass
sealed class ChannelInviteResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-Response of this request
 *
 * @property ok will be true
 * @property channel the channel in which the user was added
 */
@JacksonDataClass
data class SuccessfulChannelInviteResponse constructor(
    override val ok: Boolean,
    @JsonProperty("channel") val channel: Channel
) : ChannelInviteResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorChannelInviteResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : ChannelInviteResponse(ok) {
    companion object
}

/**
 * Invites a Users to a Channel
 *
 * @property channel the channel in which the user should be invited to
 * @property user the user that should be invited to the channel
 * @see
 */
@JacksonDataClass
data class ChannelInviteRequest constructor(
    @JsonProperty("channel") val channel: String,
    @JsonProperty("user") val user: String
) {

    companion object
}
