package com.kreait.slack.api.contract.jackson.group.chat

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.JacksonDataClass
import java.time.Instant

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulChatDeleteResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorChatDeleteResponse::class, name = "false")
)
@JacksonDataClass
sealed class ChatDeleteResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulChatDeleteResponse constructor(override val ok: Boolean,
                                                    @JsonProperty("channel") val channel: String,
                                                    @JsonProperty("ts") val timestamp: Instant)
    : ChatDeleteResponse(ok) {
    companion object
}


@JacksonDataClass
data class ErrorChatDeleteResponse constructor(override val ok: Boolean,
                                               @JsonProperty("error") val error: String)
    : ChatDeleteResponse(ok) {
    companion object
}


@JacksonDataClass
data class ChatDeleteRequest constructor(@JsonProperty("channel") val channel: String,
                                         @JsonProperty("ts") val timestamp: Instant,
                                         @JsonProperty("as_user") val asUser: Boolean = false) {
    companion object
}
