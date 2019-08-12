package com.kreait.slack.api.contract.jackson.group.channels

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.JacksonDataClass
import com.kreait.slack.api.contract.jackson.common.types.Channel

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)

@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulChannelInviteResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorChannelInviteResponse::class, name = "false")
)

@JacksonDataClass
sealed class SlackChannelInviteResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulChannelInviteResponse constructor(override val ok: Boolean,
                                                       @JsonProperty("channel") val channel: Channel)
    : SlackChannelInviteResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorChannelInviteResponse constructor(override val ok: Boolean,
                                                  @JsonProperty("error") val error: String)
    : SlackChannelInviteResponse(ok) {
    companion object
}


@JacksonDataClass
data class SlackChannelInviteRequest constructor(@JsonProperty("channel") val channel: String,
                                                 @JsonProperty("user") val user: String) {

    companion object
}
