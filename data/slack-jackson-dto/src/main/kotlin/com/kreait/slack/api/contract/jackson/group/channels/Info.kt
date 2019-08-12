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
        JsonSubTypes.Type(value = SuccessfulGetChannelInfoResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorGetChannelInfoResponse::class, name = "false")
)
@JacksonDataClass
sealed class SlackGetChannelInfoResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulGetChannelInfoResponse constructor(override val ok: Boolean,
                                                        @JsonProperty("channel") val channel: Channel)
    : SlackGetChannelInfoResponse(ok) {

    companion object
}

@JacksonDataClass
data class ErrorGetChannelInfoResponse constructor(override val ok: Boolean,
                                                   @JsonProperty("error") val error: String)
    : SlackGetChannelInfoResponse(ok) {

    companion object
}

@JacksonDataClass
data class SlackChannelsInfoRequest constructor(@JsonProperty("channel") val channel: String,
                                                @JsonProperty("include_locale") val includeLocale: Boolean = false) {
    companion object
}
