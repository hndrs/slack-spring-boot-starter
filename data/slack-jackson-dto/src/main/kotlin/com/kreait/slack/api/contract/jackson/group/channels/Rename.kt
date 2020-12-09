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
    JsonSubTypes.Type(value = SuccessfulChannelRenameResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorChannelRenameResponse::class, name = "false")
)
@JacksonDataClass
sealed class ChannelRenameResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property channel the channel object of the renamed channel
 */
@JacksonDataClass
data class SuccessfulChannelRenameResponse constructor(
    override val ok: Boolean,
    @JsonProperty("channel") val channel: Channel
) : ChannelRenameResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorChannelRenameResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : ChannelRenameResponse(ok) {
    companion object
}

/**
 * Renames a channel.
 *
 * @property channelId the channel-id of the channel you want to rename
 * @property name the new channel-name
 * @property validate Whether to return errors on invalid channel name instead of modifying it to meet the specified criteria.
 */
@JacksonDataClass
data class ChannelRenameRequest constructor(
    @JsonProperty("channel") val channelId: String,
    @JsonProperty("name") val name: String,
    @JsonProperty("validate") val validate: Boolean?
) {

    companion object
}
