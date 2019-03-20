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
        JsonSubTypes.Type(value = SuccessfulImOpenResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorImOpenResponse::class, name = "false")
)
@JacksonDataClass
abstract class SlackImOpenResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulImOpenResponse constructor(override val ok: Boolean,
                                                @JsonProperty("channel") val channel: Channel)
    : SlackImOpenResponse(ok)

@JacksonDataClass
data class ErrorImOpenResponse constructor(override val ok: Boolean, @JsonProperty("error") val error: String)
    : SlackImOpenResponse(ok)

@JacksonDataClass
data class SlackImOpenRequest constructor(@JsonProperty("user") val user: String,
                                          @JsonProperty("include_locale") val includeLocale: Boolean? = null,
                                          @JsonProperty("return_im") val returnIm: Boolean? = null)

@JacksonDataClass
data class Channel constructor(@JsonProperty("id") val id: String)
