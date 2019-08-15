package com.kreait.slack.api.contract.jackson.group.chat

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.JacksonDataClass
import java.time.Instant

@JacksonDataClass
data class ChatGetPermalinkRequest constructor(@JsonProperty("channel") val channel: String,
                                               @JsonProperty("message_ts") val timestamp: Instant) {
    companion object
}

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulChatGetPermalinkResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorChatGetPermalinkResponse::class, name = "false")
)
@JacksonDataClass
sealed class ChatGetPermalinkResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulChatGetPermalinkResponse constructor(override val ok: Boolean,
                                                          @JsonProperty("channel") val channel: String,
                                                          @JsonProperty("permalink") val permalink: String) : ChatGetPermalinkResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorChatGetPermalinkResponse constructor(override val ok: Boolean,
                                                     @JsonProperty("error") val error: String)
    : ChatGetPermalinkResponse(ok) {
    companion object
}


