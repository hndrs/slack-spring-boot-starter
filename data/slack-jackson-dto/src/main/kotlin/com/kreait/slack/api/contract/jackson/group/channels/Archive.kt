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
    JsonSubTypes.Type(value = SuccessfulChannelArchiveResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorChannelArchiveResponse::class, name = "false")
)

@JacksonDataClass
sealed class ChannelArchiveResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 */
@JacksonDataClass
data class SuccessfulChannelArchiveResponse constructor(override val ok: Boolean) : ChannelArchiveResponse(ok) {
    companion object
}


/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorChannelArchiveResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : ChannelArchiveResponse(ok) {
    companion object
}

/**
 * Archives a channel.
 *
 * @property channel the channel-id you want to archive
 * @see [Slack Api Method](https://api.slack.com/methods/channels.archive)
 */
@JacksonDataClass
data class ChannelsArchiveRequest constructor(@JsonProperty("channel") val channel: String) {

    companion object
}
