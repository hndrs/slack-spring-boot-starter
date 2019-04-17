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
        JsonSubTypes.Type(value = SuccessfulImCloseResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorImCloseResponse::class, name = "false")
)

@JacksonDataClass
abstract class SlackImCloseResponse constructor(@JsonProperty(value = "ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulImCloseResponse constructor(override val ok: Boolean,
                                                 @JsonProperty(value = "no_op") val noOp: Boolean?,
                                                 @JsonProperty(value = "already_closed") val alreadyClosed: Boolean?)
    : SlackImCloseResponse(ok){
    companion object
}

@JacksonDataClass
data class ErrorImCloseResponse constructor(override val ok: Boolean,
                                            @JsonProperty(value = "error") val error: String)
    : SlackImCloseResponse(ok){
    companion object
}

@JacksonDataClass
data class SlackImCloseRequest constructor(@JsonProperty(value = "channel") val channelId: String){
    companion object
}
