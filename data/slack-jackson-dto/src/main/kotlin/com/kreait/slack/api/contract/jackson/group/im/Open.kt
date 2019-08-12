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
        JsonSubTypes.Type(value = SuccessfulImOpenResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorImOpenResponse::class, name = "false")
)
@JacksonDataClass
sealed class ImOpenResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulImOpenResponse constructor(override val ok: Boolean,
                                                @JsonProperty("channel") val channel: Channel)
    : ImOpenResponse(ok) {
    companion object
    @JacksonDataClass
    data class Channel constructor(@JsonProperty("id") val id: String) {
        companion object
    }
}

@JacksonDataClass
data class ErrorImOpenResponse constructor(override val ok: Boolean, @JsonProperty("error") val error: String)
    : ImOpenResponse(ok) {
    companion object
}

@JacksonDataClass
data class ImOpenRequest constructor(@JsonProperty("user") val user: String,
                                     @JsonProperty("include_locale") val includeLocale: Boolean? = null,
                                     @JsonProperty("return_im") val returnIm: Boolean? = null) {
    companion object
}


