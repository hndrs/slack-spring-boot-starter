package io.olaph.slack.dto.jackson.group.chat

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.olaph.slack.dto.jackson.JacksonDataClass

@JacksonDataClass
data class SlackChatUpdateRequest constructor(@JsonProperty("channel") val channel: String,
                                              @JsonProperty("text") val text: String? = null,
                                              @JsonProperty("ts") val timestamp: String? = null,
                                              @JsonProperty("as_user") val asUser: Boolean? = true,
                                              @JsonProperty("attachments") val attachments: List<UpdateAttachment>? = null,
                                              @JsonProperty("link_names") val linkNames: Boolean? = true,
                                              @JsonProperty("parse") val parse: String? = "client")

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulChatUpdateResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorChatUpdateResponse::class, name = "false")
)
@JacksonDataClass
abstract class SlackChatUpdateResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulChatUpdateResponse constructor(override val ok: Boolean,
                                                    @JsonProperty("channel") val channel: String,
                                                    @JsonProperty("ts") val timestamp: String,
                                                    @JsonProperty("text") val text: String?)
    : SlackChatUpdateResponse(ok)

@JacksonDataClass
data class ErrorChatUpdateResponse constructor(override val ok: Boolean,
                                               @JsonProperty("error") val error: String)
    : SlackChatUpdateResponse(ok)

@JacksonDataClass
data class UpdateAttachment(
        @JsonProperty("pretext") val pretext: String? = null,
        @JsonProperty("text") val text: String,
        @JsonProperty("title") val title: String? = null,
        @JsonProperty("color") val color: String? = null,
        @JsonProperty("attachment_type") val attachmentType: String? = "default",
        @JsonProperty("callback_id") val callbackId: String? = null,
        @JsonProperty("actions") val actions: List<Action>? = listOf(),
        @JsonProperty("author_name") val authorName: String? = null,
        @JsonProperty("fallback") val fallback: String)
