package com.kreait.slack.api.contract.jackson.group.channels

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import com.kreait.slack.api.contract.jackson.common.ResponseMetadata
import com.kreait.slack.api.contract.jackson.common.types.Channel

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulChannelListResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorChannelListResponse::class, name = "false")
)
@JacksonDataClass
sealed class ChannelListResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class ErrorChannelListResponse constructor(override val ok: Boolean,
                                                @JsonProperty("error") val error: String)
    : ChannelListResponse(ok) {
    companion object
}

@JacksonDataClass
data class SuccessfulChannelListResponse(
        override val ok: Boolean,
        @JsonProperty("channels") val channels: List<Channel>,
        @JsonProperty("response_metadata") val responseMetadata: ResponseMetadata)
    : ChannelListResponse(ok) {
    companion object
}

