package com.kreait.slack.api.contract.jackson.group.channels

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.JacksonDataClass
import java.time.Instant

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)

@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulChannelMarkResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorChannelMarkResponse::class, name = "false")
)

@JacksonDataClass
sealed class ChannelMarkResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulChannelMarkResponse constructor(override val ok: Boolean)
    : ChannelMarkResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorChannelMarkResponse constructor(override val ok: Boolean,
                                                @JsonProperty("error") val error: String,
                                                @JsonProperty("detail") val detail: String)
    : ChannelMarkResponse(ok) {
    companion object
}


@JacksonDataClass
data class ChannelMarkRequest constructor(@JsonProperty("channel") val channel: String,
                                          @JsonProperty("ts") val timestamp: Instant) {

    companion object
}
