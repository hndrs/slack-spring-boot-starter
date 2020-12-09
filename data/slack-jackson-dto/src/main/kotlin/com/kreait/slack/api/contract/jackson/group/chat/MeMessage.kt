package com.kreait.slack.api.contract.jackson.group.chat

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.util.InstantToString
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import java.time.Instant

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "ok",
    visible = true
)
@JsonSubTypes(
    JsonSubTypes.Type(value = SuccessfulChatMeMessageResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorChatMeMessageResponse::class, name = "false")
)
@JacksonDataClass
sealed class ChatMeMessageResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 */
@JacksonDataClass
data class SuccessfulChatMeMessageResponse constructor(
    override val ok: Boolean,
    @JsonProperty("channel") val channelId: String,
    @InstantToString @JsonProperty("ts") val timestamp: Instant
) : ChatMeMessageResponse(ok) {
    companion object
}


/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorChatMeMessageResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : ChatMeMessageResponse(ok) {
    companion object
}

/**
 * Sends a me message to a channel from the calling user.
 *
 * @property channelId Channel to send message to. Can be a public channel, private group or IM channel. Can be an encoded ID, or a name.
 * @property text The message-text
 */
@JacksonDataClass
data class ChatMeMessageRequest constructor(
    @JsonProperty("channel") val channelId: String,
    @JsonProperty("text") val text: String
) {
    companion object
}
