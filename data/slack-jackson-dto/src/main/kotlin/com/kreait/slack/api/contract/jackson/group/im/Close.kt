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
        JsonSubTypes.Type(value = SuccessfulImCloseResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorImCloseResponse::class, name = "false")
)

@JacksonDataClass
sealed class ImCloseResponse constructor(@JsonProperty(value = "ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulImCloseResponse constructor(override val ok: Boolean,
                                                 @JsonProperty(value = "no_op") val noOp: Boolean?,
                                                 @JsonProperty(value = "already_closed") val alreadyClosed: Boolean?)
    : ImCloseResponse(ok){
    companion object
}

@JacksonDataClass
data class ErrorImCloseResponse constructor(override val ok: Boolean,
                                            @JsonProperty(value = "error") val error: String)
    : ImCloseResponse(ok){
    companion object
}

@JacksonDataClass
data class ImCloseRequest constructor(@JsonProperty(value = "channel") val channelId: String){
    companion object
}
