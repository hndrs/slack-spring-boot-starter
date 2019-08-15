package com.kreait.slack.api.contract.jackson.group.channels

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)

@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulChannelUnarchiveResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorChannelUnarchiveResponse::class, name = "false")
)

@JacksonDataClass
sealed class ChannelUnarchiveResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulChannelUnarchiveResponse constructor(override val ok: Boolean)
    : ChannelUnarchiveResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorChannelUnarchiveResponse constructor(override val ok: Boolean,
                                                     @JsonProperty("error") val error: String)
    : ChannelUnarchiveResponse(ok) {
    companion object
}

@JacksonDataClass
data class ChannelsUnarchiveRequest constructor(@JsonProperty("channel") val channel: String) {

    companion object
}
