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
        JsonSubTypes.Type(value = SuccessfulChannelCreateResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorChannelCreateResponse::class, name = "false")
)

@JacksonDataClass
sealed class ChannelCreateResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulChannelCreateResponse constructor(override val ok: Boolean,
                                                       @JsonProperty("channel") val channel: Channel)
    : ChannelCreateResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorChannelCreateResponse constructor(override val ok: Boolean,
                                                  @JsonProperty("error") val error: String,
                                                  @JsonProperty("detail") val detail: String)
    : ChannelCreateResponse(ok) {
    companion object
}


@JacksonDataClass
data class ChannelCreateRequest constructor(@JsonProperty("name") val name: String,
                                            @JsonProperty("validate") val validate: Boolean?) {

    companion object
}
