package io.olaph.slack.dto.jackson.group.channels

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.olaph.slack.dto.jackson.JacksonDataClass
import io.olaph.slack.dto.jackson.common.types.Channel

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulGetChannelListResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorGetChannelListResponse::class, name = "false")
)
@JacksonDataClass
sealed class SlackGetChannelListResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class ErrorGetChannelListResponse constructor(override val ok: Boolean,
                                                   @JsonProperty("error") val error: String)
    : SlackGetChannelListResponse(ok) {
    companion object
}

@JacksonDataClass
data class SuccessfulGetChannelListResponse(
        override val ok: Boolean,
        @JsonProperty("channels") val channels: List<Channel>) : SlackGetChannelListResponse(ok) {
    companion object
}

