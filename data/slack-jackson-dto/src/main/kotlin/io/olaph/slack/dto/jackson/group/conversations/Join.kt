package io.olaph.slack.dto.jackson.group.conversations

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.olaph.slack.dto.jackson.JacksonDataClass
import io.olaph.slack.dto.jackson.common.ResponseMetadata

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulConversationJoinResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorConversationJoinResponse::class, name = "false")
)

@JacksonDataClass
sealed class ConversationJoinResponse(@JsonProperty("ok") open val ok: Boolean)

/**
 * [SlackDoc](https://api.slack.com/methods/conversations.join)
 */
data class SuccessfulConversationJoinResponse(override val ok: Boolean,
                                              @JsonProperty("channel") val channel: Channel,
                                              @JsonProperty("warning") val warning: String?,
                                              @JsonProperty("response_metadata") val responseMetadata: ResponseMetadata?)
    : ConversationJoinResponse(ok) {
    companion object

    @JacksonDataClass
    data class Channel(@JsonProperty("id") val id: String) {
        companion object
    }

}

/**
 * [SlackDoc](https://api.slack.com/methods/conversations.join)
 */
@JacksonDataClass
data class ErrorConversationJoinResponse constructor(override val ok: Boolean,
                                                     @JsonProperty("error") val error: String)
    : ConversationJoinResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/conversations.join)
 */
data class ConversationJoinRequest(@JsonProperty("channel") val channel: String) {
    companion object
}
