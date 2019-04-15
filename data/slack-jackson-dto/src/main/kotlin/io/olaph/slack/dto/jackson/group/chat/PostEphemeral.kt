package io.olaph.slack.dto.jackson.group.chat

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.olaph.slack.dto.jackson.JacksonDataClass
import io.olaph.slack.dto.jackson.common.messaging.Attachment
import io.olaph.slack.dto.jackson.common.messaging.Block

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulPostEphemeralResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorPostEphemeralResponse::class, name = "false")
)
@JacksonDataClass
sealed class SlackPostEphemeralResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulPostEphemeralResponse constructor(override val ok: Boolean,
                                                       @JsonProperty("message_ts") val timestamp: String)
    : SlackPostEphemeralResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorPostEphemeralResponse constructor(override val ok: Boolean,
                                                  @JsonProperty("error") val error: String)
    : SlackPostEphemeralResponse(ok) {
    companion object
}

@JacksonDataClass
data class SlackPostEphemeralRequest constructor(@JsonProperty("text") val text: String? = null,
                                                 @JsonProperty("attachments") val attachments: List<Attachment>? = null,
                                                 @JsonProperty("blocks") val blocks: List<Block>? = null,
                                                 @JsonProperty("channel") val channel: String,
                                                 @JsonProperty("as_user") val asUser: Boolean = false,
                                                 @JsonProperty("user") val user: String? = null,
                                                 @JsonProperty("link_names") val linkNames: Boolean = true,
                                                 @JsonProperty("parse") val parse: String? = null,
                                                 @JsonProperty("thread_ts") val threadTs: String? = null) {
    companion object
}
