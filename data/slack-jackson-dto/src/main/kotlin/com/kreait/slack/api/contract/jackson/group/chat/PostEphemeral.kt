package com.kreait.slack.api.contract.jackson.group.chat

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.common.messaging.Attachment
import com.kreait.slack.api.contract.jackson.common.messaging.Block
import com.kreait.slack.api.contract.jackson.util.InstantToString
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import java.time.Instant

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulPostEphemeralResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorPostEphemeralResponse::class, name = "false")
)
@JacksonDataClass
sealed class PostEphemeralResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulPostEphemeralResponse constructor(override val ok: Boolean,
                                                       @InstantToString @JsonProperty("message_ts") val timestamp: Instant)
    : PostEphemeralResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorPostEphemeralResponse constructor(override val ok: Boolean,
                                                  @JsonProperty("error") val error: String)
    : PostEphemeralResponse(ok) {
    companion object
}

@JacksonDataClass
data class PostEphemeralRequest constructor(@JsonProperty("text") val text: String? = null,
                                            @JsonProperty("attachments") val attachments: List<Attachment>? = null,
                                            @JsonProperty("blocks") val blocks: List<Block>? = null,
                                            @JsonProperty("channel") val channel: String,
                                            @JsonProperty("as_user") val asUser: Boolean = false,
                                            @JsonProperty("user") val user: String? = null,
                                            @JsonProperty("link_names") val linkNames: Boolean = true,
                                            @JsonProperty("parse") val parse: String? = null,
                                            @InstantToString @JsonProperty("thread_ts") val threadTimestamp: Instant? = null) {
    companion object
}
