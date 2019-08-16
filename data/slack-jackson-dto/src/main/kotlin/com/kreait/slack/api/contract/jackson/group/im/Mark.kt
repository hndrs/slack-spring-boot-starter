package com.kreait.slack.api.contract.jackson.group.im

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.util.InstantToString
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import java.time.Instant

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)

@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulImMarkResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorImMarkResponse::class, name = "false")
)

@JacksonDataClass
sealed class ImMarkResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulImMarkResponse constructor(override val ok: Boolean)
    : ImMarkResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorImMarkResponse constructor(override val ok: Boolean,
                                           @JsonProperty("error") val error: String)
    : ImMarkResponse(ok) {
    companion object
}

@JacksonDataClass
data class ImMarkRequest constructor(@JsonProperty("channel") val channel: String,
                                     @InstantToString @JsonProperty("ts") val timestamp: Instant) {
    companion object
}
