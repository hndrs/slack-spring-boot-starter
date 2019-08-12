package com.kreait.slack.api.contract.jackson.group.im

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.JacksonDataClass

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)

@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulImMarkResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorImMarkResponse::class, name = "false")
)

@JacksonDataClass
sealed class SlackImMarkResponse constructor(@JsonProperty(value = "ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulImMarkResponse constructor(override val ok: Boolean)
    : SlackImMarkResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorImMarkResponse constructor(override val ok: Boolean,
                                           @JsonProperty(value = "error") val error: String)
    : SlackImMarkResponse(ok) {
    companion object
}

@JacksonDataClass
data class SlackImMarkRequest constructor(@JsonProperty(value = "channel") val channel: String,
                                          @JsonProperty(value = "ts") val ts: String) {
    companion object
}
