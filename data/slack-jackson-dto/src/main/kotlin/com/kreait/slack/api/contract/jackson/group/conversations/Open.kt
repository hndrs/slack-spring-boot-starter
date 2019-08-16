package com.kreait.slack.api.contract.jackson.group.conversations

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulConversationOpenResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorConversationOpenResponse::class, name = "false")
)
@JacksonDataClass
sealed class ConversationOpenResponse constructor(@JsonProperty("ok") open val ok: Boolean) {
    companion object
}

@JacksonDataClass
data class SuccessfulConversationOpenResponse constructor(override val ok: Boolean,
                                                          @JsonProperty("channel") val channel: Channel)
    : ConversationOpenResponse(ok) {

    companion object


    @JacksonDataClass
    data class Channel(@JsonProperty("id") val id: String) {
        companion object
    }
}

@JacksonDataClass
data class ErrorConversationOpenResponse constructor(override val ok: Boolean, @JsonProperty("error") val error: String)
    : ConversationOpenResponse(ok) {
    companion object
}

@JacksonDataClass
data class ConversationsOpenRequest constructor(@JsonProperty("channel") val channelId: String? = null,
                                                @JsonProperty("return_im") val shouldReturnIm: Boolean? = null,
                                                @JsonProperty("users") val users: List<String>? = null) {
    companion object
}


