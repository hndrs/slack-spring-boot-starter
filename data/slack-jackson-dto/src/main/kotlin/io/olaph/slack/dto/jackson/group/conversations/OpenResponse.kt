package io.olaph.slack.dto.jackson.group.conversations

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.olaph.slack.dto.jackson.JacksonDataClass

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulOpenResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorOpenResponse::class, name = "false")
)
@JacksonDataClass
abstract class SlackOpenResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulOpenResponse constructor(override val ok: Boolean,
                                              @JsonProperty("channel") val channel: Channel)
    : SlackOpenResponse(ok)

@JacksonDataClass
data class ErrorOpenResponse constructor(override val ok: Boolean, @JsonProperty("error") val error: String)
    : SlackOpenResponse(ok)

@JacksonDataClass
data class SlackOpenRequest constructor(@JsonProperty("users") val users: List<String>)

@JacksonDataClass
data class Channel(val id: String)
