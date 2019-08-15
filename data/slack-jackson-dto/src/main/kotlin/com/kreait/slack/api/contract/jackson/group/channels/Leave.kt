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
        JsonSubTypes.Type(value = SuccessfulChannelsLeaveResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorChannelsLeaveResponse::class, name = "false")
)

@JacksonDataClass
sealed class ChannelsLeaveResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulChannelsLeaveResponse constructor(override val ok: Boolean)
    : ChannelsLeaveResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorChannelsLeaveResponse constructor(override val ok: Boolean,
                                                  @JsonProperty("error") val error: String,
                                                  @JsonProperty("detail") val detail: String)
    : ChannelsLeaveResponse(ok) {
    companion object
}


@JacksonDataClass
data class ChannelsLeaveRequest constructor(@JsonProperty("channel") val channelId: String) {

    companion object
}
