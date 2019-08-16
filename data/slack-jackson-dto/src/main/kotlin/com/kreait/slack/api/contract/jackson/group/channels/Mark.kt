package com.kreait.slack.api.contract.jackson.group.channels

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import java.time.Instant

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)

@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulChannelsMarkResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorChannelsMarkResponse::class, name = "false")
)

@JacksonDataClass
sealed class ChannelsMarkResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulChannelsMarkResponse constructor(override val ok: Boolean)
    : ChannelsMarkResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorChannelsMarkResponse constructor(override val ok: Boolean,
                                                 @JsonProperty("error") val error: String,
                                                 @JsonProperty("detail") val detail: String)
    : ChannelsMarkResponse(ok) {
    companion object
}


@JacksonDataClass
data class ChannelsMarkRequest constructor(@JsonProperty("channel") val channelId: String,
                                           @JsonProperty("ts") val timestamp: Instant) {

    companion object
}
