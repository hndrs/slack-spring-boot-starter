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
        JsonSubTypes.Type(value = SuccessfulChannelSetPurposeResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorChannelSetPurposeResponse::class, name = "false")
)

@JacksonDataClass
sealed class ChannelSetPurposeResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulChannelSetPurposeResponse constructor(override val ok: Boolean,
                                                           @JsonProperty("purpose") val purpose: String)
    : ChannelSetPurposeResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorChannelSetPurposeResponse constructor(override val ok: Boolean,
                                                      @JsonProperty("error") val error: String)
    : ChannelSetPurposeResponse(ok) {
    companion object
}


@JacksonDataClass
data class ChannelSetPurposeRequest constructor(@JsonProperty("channel") val channel: String,
                                                @JsonProperty("purpose") val purpose: String,
                                                @JsonProperty("name_tagging") val nameTagging: Boolean = true) {

    companion object
}
