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
    JsonSubTypes.Type(value = SuccessfulChannelUnarchiveResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorChannelUnarchiveResponse::class, name = "false")
)

@JacksonDataClass
sealed class ChannelUnarchiveResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 */
@JacksonDataClass
data class SuccessfulChannelUnarchiveResponse constructor(override val ok: Boolean) : ChannelUnarchiveResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorChannelUnarchiveResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : ChannelUnarchiveResponse(ok) {
    companion object
}

/**
 * Unarchives a channel.
 *
 * @property channelId the channel-id you want to unarchive
 */
@JacksonDataClass
data class ChannelsUnarchiveRequest constructor(@JsonProperty("channel") val channelId: String) {

    companion object
}
