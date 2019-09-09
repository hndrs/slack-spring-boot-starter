package com.kreait.slack.api.contract.jackson.group.chat

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.common.messaging.UpdateAttachment
import com.kreait.slack.api.contract.jackson.util.InstantToString
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import java.time.Instant

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

/**
 * Success-response of this request.
 *
 * @property ok will be true
 */
@JacksonDataClass
data class SuccessfulChatUpdateResponse constructor(override val ok: Boolean,
                                                    @JsonProperty("channel") val channel: String,
                                                    @InstantToString @JsonProperty("ts") val timestamp: Instant,
                                                    @JsonProperty("text") val text: String?)
    : ChatUpdateResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorChatUpdateResponse constructor(override val ok: Boolean,
                                               @JsonProperty("error") val error: String)
    : ChatUpdateResponse(ok) {
    companion object
}

/**
 * This method updates a message in a channel. Though related to chat.postMessage, some parameters of chat.update are handled differently.
 * Ephemeral messages created by chat.postEphemeral or otherwise cannot be updated with this method.
 *
 * @property channel the channel-id in which the message exists
 * @property text the new text of the message
 * @property timestamp the timestamp of the message you want to update
 * @property asUser Pass true to update the message as the authed user. Bot users in this context are considered authed users.
 * @property attachments new attachments
 * @property linkNames Find and link channel names and usernames. Defaults to none.
 * @property parse the @link[ParseType] of that message
 */
@JacksonDataClass
data class ChatUpdateRequest constructor(@JsonProperty("channel") val channel: String,
                                         @JsonProperty("text") val text: String? = null,
                                         @InstantToString @JsonProperty("ts") val timestamp: Instant,
                                         @JsonProperty("as_user") val asUser: Boolean? = true,
                                         @JsonProperty("attachments") val attachments: List<UpdateAttachment>? = null,
                                         @JsonProperty("link_names") val linkNames: Boolean? = true,
                                         @JsonProperty("parse") val parse: ParseType? = null) {
    companion object
}