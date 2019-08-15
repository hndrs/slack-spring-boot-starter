package com.kreait.slack.api.contract.jackson.group.channels

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.JacksonDataClass

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulChannelKickResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorChannelKickResponse::class, name = "false")
)
@JacksonDataClass
sealed class ChannelKickResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulChannelKickResponse constructor(override val ok: Boolean)
    : ChannelKickResponse(ok) {

    companion object
}

@JacksonDataClass
data class ErrorChannelKickResponse constructor(override val ok: Boolean,
                                                @JsonProperty("error") val error: String)
    : ChannelKickResponse(ok) {

    companion object
}

@JacksonDataClass
data class ChannelsKickRequest constructor(@JsonProperty("channel") val channel: String,
                                           @JsonProperty("user") val user: String) {
    companion object {}

    fun toRequestMap() = mapOf("channel" to channel, "user" to user)

}
