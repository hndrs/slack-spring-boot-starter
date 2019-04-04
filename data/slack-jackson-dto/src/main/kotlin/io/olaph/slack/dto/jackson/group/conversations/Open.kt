package io.olaph.slack.dto.jackson.group.conversations

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.olaph.slack.dto.jackson.JacksonDataClass

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulConversationOpenResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorConversationOpenResponse::class, name = "false")
)
@JacksonDataClass
abstract class ConversationOpenResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulConversationOpenResponse constructor(override val ok: Boolean,
                                                          @JsonProperty("channel") val channel: Channel)
    : ConversationOpenResponse(ok) {

    @JacksonDataClass
    data class Channel(@JsonProperty("id") val id: String)
}

@JacksonDataClass
data class ErrorConversationOpenResponse constructor(override val ok: Boolean, @JsonProperty("error") val error: String)
    : ConversationOpenResponse(ok)

@JacksonDataClass
data class ConversationsOpenRequest constructor(@JsonProperty("channel") val channelId: String? = null,
                                                @JsonProperty("return_im") val shouldReturnIm: Boolean? = null,
                                                @JsonProperty("users") val users: List<String>? = null)


