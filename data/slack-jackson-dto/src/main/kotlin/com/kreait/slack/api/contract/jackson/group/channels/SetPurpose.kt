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
    JsonSubTypes.Type(value = SuccessfulChannelsSetPurposeResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorChannelsSetPurposeResponse::class, name = "false")
)

@JacksonDataClass
sealed class ChannelsSetPurposeResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property purpose the purpose you set
 */
@JacksonDataClass
data class SuccessfulChannelsSetPurposeResponse constructor(
    override val ok: Boolean,
    @JsonProperty("purpose") val purpose: String
) : ChannelsSetPurposeResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorChannelsSetPurposeResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : ChannelsSetPurposeResponse(ok) {
    companion object
}

/**
 * Sets the purpose for a channel.
 *
 * @property channel the channelId of which you want to set the purpose
 * @property purpose the purpose you want to set
 * @property nameTagging true if names and mentions should be resolved
 */
@JacksonDataClass
data class ChannelsSetPurposeRequest constructor(
    @JsonProperty("channel") val channel: String,
    @JsonProperty("purpose") val purpose: String,
    @JsonProperty("name_tagging") val nameTagging: Boolean? = true
) {

    companion object
}
