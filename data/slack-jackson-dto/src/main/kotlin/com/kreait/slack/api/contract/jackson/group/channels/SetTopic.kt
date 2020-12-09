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
    JsonSubTypes.Type(value = SuccessfulChannelsSetTopicResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorChannelsSetTopicResponse::class, name = "false")
)

@JacksonDataClass
sealed class ChannelsSetTopicResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property channel the channel object with the changed topic
 */
@JacksonDataClass
data class SuccessfulChannelsSetTopicResponse constructor(
    override val ok: Boolean,
    @JsonProperty("channel") val channel: Channel
) : ChannelsSetTopicResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorChannelsSetTopicResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String,
    @JsonProperty("detail") val detail: String
) : ChannelsSetTopicResponse(ok) {
    companion object
}

/**
 * Sets the topic for a channel.
 *
 * @property channelId Channel to set the topic of
 * @property topic the topic you want to set
 */
@JacksonDataClass
data class ChannelsSetTopicRequest constructor(
    @JsonProperty("channel") val channelId: String,
    @JsonProperty("topic") val topic: String
) {

    companion object
}
