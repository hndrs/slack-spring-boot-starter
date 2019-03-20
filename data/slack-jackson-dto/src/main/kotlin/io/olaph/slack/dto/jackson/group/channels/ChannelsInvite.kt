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
        JsonSubTypes.Type(value = SuccessfulChannelInviteResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorChannelInviteResponse::class, name = "false")
)

@JacksonDataClass
abstract class SlackChannelInviteResponse constructor(@JsonProperty("ok") open val ok: Boolean)

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
