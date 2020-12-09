package com.kreait.slack.api.contract.jackson.group.channels

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.util.InstantToString
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import java.time.Instant

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "ok",
    visible = true
)

@JsonSubTypes(
    JsonSubTypes.Type(value = SuccessfulChannelsMarkResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorChannelsMarkResponse::class, name = "false")
)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 */
@JacksonDataClass
sealed class ChannelsMarkResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulChannelsMarkResponse constructor(override val ok: Boolean) : ChannelsMarkResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorChannelsMarkResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String,
    @JsonProperty("detail") val detail: String
) : ChannelsMarkResponse(ok) {
    companion object
}

/**
 * Sets the read cursor in a channel.
 *
 * @property channelId the channel-id on which the read cursor should be set
 * @property timestamp Timestamp of the most recently seen message.
 */
@JacksonDataClass
data class ChannelsMarkRequest constructor(
    @JsonProperty("channel") val channelId: String,
    @InstantToString @JsonProperty("ts") val timestamp: Instant
) {

    companion object
}
