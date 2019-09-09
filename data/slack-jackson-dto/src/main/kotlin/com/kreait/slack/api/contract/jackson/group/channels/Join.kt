package com.kreait.slack.api.contract.jackson.group.channels

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.common.types.Channel
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulChannelsJoinResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorChannelsJoinResponse::class, name = "false")
)
@JacksonDataClass
sealed class ChannelsJoinResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property channel the channel object which the user joined
 * @property alreadyInChannel true if the user was already in the channel
 */
@JacksonDataClass
data class SuccessfulChannelsJoinResponse constructor(override val ok: Boolean,
                                                      @JsonProperty("channel") val channel: Channel,
                                                      @JsonProperty("already_in_channel") val alreadyInChannel: Boolean = false)
    : ChannelsJoinResponse(ok) {

    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorChannelsJoinResponse constructor(override val ok: Boolean,
                                                 @JsonProperty("error") val error: String)
    : ChannelsJoinResponse(ok) {

    companion object
}

/**
 * Joins a channel, creating it if needed.
 *
 * @property name Name of channel to join
 * @property validate Whether to return errors on invalid channel name instead of modifying it to meet the specified criteria.
 */

@JacksonDataClass
data class ChannelsJoinRequest constructor(@JsonProperty("name") val name: String,
                                           @JsonProperty("validate") val validate: Boolean = false) {
    companion object
}
