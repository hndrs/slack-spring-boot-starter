package com.kreait.slack.api.contract.jackson.group.channels

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
    JsonSubTypes.Type(value = SuccessfulChannelKickResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorChannelKickResponse::class, name = "false")
)
@JacksonDataClass
sealed class ChannelKickResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 */
@JacksonDataClass
data class SuccessfulChannelKickResponse constructor(override val ok: Boolean) : ChannelKickResponse(ok) {

    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorChannelKickResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : ChannelKickResponse(ok) {

    companion object
}

/**
 * Removes a user from a channel.
 *
 * @property channelId the channel-id from which the user should be removed
 * @property userId the user-id that should be removed
 */
@JacksonDataClass
data class ChannelsKickRequest constructor(
    @JsonProperty("channel") val channelId: String,
    @JsonProperty("user") val userId: String
) {

    fun toRequestMap() = mapOf("channel" to channelId, "user" to userId)

    companion object
}
