package com.kreait.slack.api.contract.jackson.group.channels

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.common.types.Channel
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)

@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulChannelsCreateResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorChannelsCreateResponse::class, name = "false")
)

@JacksonDataClass
sealed class ChannelsCreateResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulChannelsCreateResponse constructor(override val ok: Boolean,
                                                        @JsonProperty("channel") val channel: Channel)
    : ChannelsCreateResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorChannelsCreateResponse constructor(override val ok: Boolean,
                                                   @JsonProperty("error") val error: String,
                                                   @JsonProperty("detail") val detail: String)
    : ChannelsCreateResponse(ok) {
    companion object
}


@JacksonDataClass
data class ChannelsCreateRequest constructor(@JsonProperty("name") val name: String,
                                             @JsonProperty("validate") val validate: Boolean?) {

    companion object
}
