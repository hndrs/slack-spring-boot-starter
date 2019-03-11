package io.olaph.slack.dto.jackson.group.im

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.olaph.slack.dto.jackson.JacksonDataClass

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)

@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulImMarkResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorImMarkResponse::class, name = "false")
)

@JacksonDataClass
abstract class SlackImMarkResponse constructor(@JsonProperty(value = "ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulImMarkResponse constructor(override val ok: Boolean)
    : SlackImMarkResponse(ok)

@JacksonDataClass
data class ErrorImMarkResponse constructor(override val ok: Boolean,
                                           @JsonProperty(value = "error") val error: String)
    : SlackImMarkResponse(ok)

@JacksonDataClass
data class SlackImMarkRequest constructor(@JsonProperty(value = "channel") val channel: String,
                                          @JsonProperty(value = "ts") val ts: String)