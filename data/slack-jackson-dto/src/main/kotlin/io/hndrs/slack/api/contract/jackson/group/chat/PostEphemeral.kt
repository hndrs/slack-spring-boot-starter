package io.hndrs.slack.api.contract.jackson.group.chat

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.hndrs.slack.api.contract.jackson.common.messaging.Attachment
import io.hndrs.slack.api.contract.jackson.common.messaging.Block
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
    JsonSubTypes.Type(value = SuccessfulPostEphemeralResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorPostEphemeralResponse::class, name = "false")
)
@JacksonDataClass
sealed class PostEphemeralResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property messageTimestamp the message-timestamp of the posted message
 */
@JacksonDataClass
data class SuccessfulPostEphemeralResponse constructor(
    override val ok: Boolean,
    @InstantToString @JsonProperty("message_ts") val messageTimestamp: Instant
) : PostEphemeralResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorPostEphemeralResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : PostEphemeralResponse(ok) {
    companion object
}

/**
 * Sends an ephemeral message to a user in a channel.
 * Ephemeral Messages are not persisted in the database and can thus not be modified or deleted
 *
 * @property text the text of the message
 * @property attachments secondary attachments
 * @property blocks secondary block items
 * @property channel the channel in which the ephemeral-message should be shown
 * @property asUser Pass true to post the message as the authed user. Defaults to true if the chat:write:bot scope is not included. Otherwise, defaults to false.
 * @property user the user-id that should receive the ephemeral message
 * @property linkNames Find and link channel names and usernames.
 * @property parse the parse-type for this message
 * @property threadTimestamp Provide another message's ts value to post this message in a thread. Avoid using a reply's ts value; use its parent's value instead. Ephemeral messages in threads are only shown if there is already an active thread.
 */
@JacksonDataClass
data class PostEphemeralRequest constructor(
    @JsonProperty("text") val text: String? = null,
    @JsonProperty("attachments") val attachments: List<Attachment>? = null,
    @JsonProperty("blocks") val blocks: List<Block>? = null,
    @JsonProperty("channel") val channel: String,
    @JsonProperty("as_user") val asUser: Boolean = false,
    @JsonProperty("user") val user: String,
    @JsonProperty("link_names") val linkNames: Boolean = true,
    @JsonProperty("parse") val parse: String? = null,
    @InstantToString @JsonProperty("thread_ts") val threadTimestamp: Instant? = null
) {
    companion object
}
