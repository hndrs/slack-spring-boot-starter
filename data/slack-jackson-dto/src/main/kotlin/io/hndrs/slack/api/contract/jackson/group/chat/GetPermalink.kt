package io.hndrs.slack.api.contract.jackson.group.chat

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.hndrs.slack.api.contract.jackson.util.InstantToString
import io.hndrs.slack.api.contract.jackson.util.JacksonDataClass
import java.time.Instant

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "ok",
    visible = true
)
@JsonSubTypes(
    JsonSubTypes.Type(value = SuccessfulChatGetPermalinkResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorChatGetPermalinkResponse::class, name = "false")
)
@JacksonDataClass
sealed class ChatGetPermalinkResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property channelId the channel-id of the message
 * @property permalink the requested permalink
 */
@JacksonDataClass
data class SuccessfulChatGetPermalinkResponse constructor(
    override val ok: Boolean,
    @JsonProperty("channel") val channelId: String,
    @JsonProperty("permalink") val permalink: String
) : ChatGetPermalinkResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorChatGetPermalinkResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : ChatGetPermalinkResponse(ok) {
    companion object
}

/**
 * Retrieve a permalink URL for a specific extant message
 *
 * @property channel the channel-id of the message
 * @property timestamp the timestamp of the message
 */
@JacksonDataClass
data class ChatGetPermalinkRequest constructor(
    @JsonProperty("channel") val channel: String,
    @InstantToString @JsonProperty("message_ts") val timestamp: Instant
) {
    companion object
}
