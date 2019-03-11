package io.olaph.slack.dto.jackson.group.chat

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.olaph.slack.dto.jackson.JacksonDataClass

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulPostEphemeralMessageResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorPostEphemeralMessageResponse::class, name = "false")
)
@JacksonDataClass
abstract class SlackPostEphemeralMessageResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulPostEphemeralMessageResponse constructor(override val ok: Boolean,
                                                              @JsonProperty("message_ts") val timestamp: String)
    : SlackPostEphemeralMessageResponse(ok)

@JacksonDataClass
data class ErrorPostEphemeralMessageResponse constructor(override val ok: Boolean,
                                                         @JsonProperty("error") val error: String)
    : SlackPostEphemeralMessageResponse(ok)

@JacksonDataClass
data class SlackPostEphemeralMessageRequest constructor(@JsonProperty("text") val text: String? = null,
                                                        @JsonProperty("attachments") val attachments: List<Attachment>? = listOf(),
                                                        @JsonProperty("channel") val channel: String,
                                                        @JsonProperty("as_user") val asUser: Boolean = false,
                                                        @JsonProperty("user") val user: String? = null,
                                                        @JsonProperty("link_names") val linkNames: Boolean = true,
                                                        @JsonProperty("parse") val parse: String = "none",
                                                        @JsonProperty("thread_ts") val threadTs: String? = null) {
    companion object
}
