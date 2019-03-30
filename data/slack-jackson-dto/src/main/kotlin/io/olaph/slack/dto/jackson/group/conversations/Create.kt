package io.olaph.slack.dto.jackson.group.conversations

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.olaph.slack.dto.jackson.JacksonDataClass
import io.olaph.slack.dto.jackson.common.types.Channel

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulConversationCreateResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorConversationCreateResponse::class, name = "false")
)
@JacksonDataClass
sealed class SlackConversationCreateResponse constructor(@JsonProperty("ok") open val ok: Boolean)

data class SuccessfulConversationCreateResponse(
        override val ok: Boolean,
        @JsonProperty("channel") val channel: Channel
) : SlackConversationCreateResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorConversationCreateResponse constructor(override val ok: Boolean,
                                                       @JsonProperty("error") val error: String)
    : SlackConversationCreateResponse(ok) {
    companion object
}

data class ConversationCreateRequest(@JsonProperty("name") val name: String,
                                     @JsonProperty("is_private") val isPrivate: Boolean? = null) {
    companion object
}
