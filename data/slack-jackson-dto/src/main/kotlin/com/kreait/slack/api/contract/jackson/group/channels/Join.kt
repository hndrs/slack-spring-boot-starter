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

@JacksonDataClass
data class SuccessfulChannelsJoinResponse constructor(override val ok: Boolean,
                                                      @JsonProperty("channel") val channel: Channel,
                                                      @JsonProperty("already_in_channel") val alreadyInChannel: Boolean = false)
    : ChannelsJoinResponse(ok) {

    companion object
}

@JacksonDataClass
data class ErrorChannelsJoinResponse constructor(override val ok: Boolean,
                                                 @JsonProperty("error") val error: String)
    : ChannelsJoinResponse(ok) {

    companion object
}

@JacksonDataClass
data class ChannelsJoinRequest constructor(@JsonProperty("name") val name: String,
                                           @JsonProperty("validate") val validate: Boolean = false) {
    companion object
}
