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
    JsonSubTypes.Type(value = SuccessfulChannelInfoResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorChannelInfoResponse::class, name = "false")
)
@JacksonDataClass
sealed class ChannelInfoResponse constructor(@JsonProperty("ok") open val ok: Boolean)


/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property channel the channel object with all information
 */
@JacksonDataClass
data class SuccessfulChannelInfoResponse constructor(
    override val ok: Boolean,
    @JsonProperty("channel") val channel: Channel
) : ChannelInfoResponse(ok) {

    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorChannelInfoResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : ChannelInfoResponse(ok) {

    companion object
}


/**
 * Gets information about a channel.
 *
 * @property channel Channel to get info on
 * @property includeLocale Set this to true to receive the locale for this channel. Defaults to false
 * @see [Slack Api Method](https://api.slack.com/methods/channels.info)
 * */
@JacksonDataClass
data class ChannelsInfoRequest constructor(
    @JsonProperty("channel") val channel: String,
    @JsonProperty("include_locale") val includeLocale: Boolean = false
) {
    companion object
}
