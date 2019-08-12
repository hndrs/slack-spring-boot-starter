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
        JsonSubTypes.Type(value = SuccessfulChannelArchiveResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorChannelArchiveResponse::class, name = "false")
)

@JacksonDataClass
sealed class SlackGetChannelArchiveResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulChannelArchiveResponse constructor(override val ok: Boolean)
    : SlackGetChannelArchiveResponse(ok){
    companion object
}

@JacksonDataClass
data class ErrorChannelArchiveResponse constructor(override val ok: Boolean,
                                                   @JsonProperty("error") val error: String)
    : SlackGetChannelArchiveResponse(ok) {
    companion object
}

@JacksonDataClass
data class SlackChannelsArchiveRequest constructor(@JsonProperty("channel") val channel: String) {

    companion object
}
