package com.kreait.slack.api.contract.jackson.group.chat

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.JacksonDataClass
import com.kreait.slack.api.contract.jackson.common.messaging.UpdateAttachment

@JacksonDataClass
data class ChatUpdateRequest constructor(@JsonProperty("channel") val channel: String,
                                         @JsonProperty("text") val text: String? = null,
                                         @JsonProperty("ts") val timestamp: String? = null,
                                         @JsonProperty("as_user") val asUser: Boolean? = true,
                                         @JsonProperty("attachments") val attachments: List<UpdateAttachment>? = null,
                                         @JsonProperty("link_names") val linkNames: Boolean? = true,
                                         @JsonProperty("parse") val parse: String? = "client") {
    companion object
}

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulChatUpdateResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorChatUpdateResponse::class, name = "false")
)
@JacksonDataClass
sealed class ChatUpdateResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulChatUpdateResponse constructor(override val ok: Boolean,
                                                    @JsonProperty("channel") val channel: String,
                                                    @JsonProperty("ts") val timestamp: String,
                                                    @JsonProperty("text") val text: String?)
    : ChatUpdateResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorChatUpdateResponse constructor(override val ok: Boolean,
                                               @JsonProperty("error") val error: String)
    : ChatUpdateResponse(ok) {
    companion object
}


