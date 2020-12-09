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
    JsonSubTypes.Type(value = SuccessfulChatDeleteResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorChatDeleteResponse::class, name = "false")
)
@JacksonDataClass
sealed class ChatDeleteResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property channel the channel-id of the channel in which the message was in
 * @property timestamp the timestamp of the deleted message
 */
@JacksonDataClass
data class SuccessfulChatDeleteResponse constructor(
    override val ok: Boolean,
    @JsonProperty("channel") val channel: String,
    @InstantToString @JsonProperty("ts") val timestamp: Instant
) : ChatDeleteResponse(ok) {
    companion object
}


/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorChatDeleteResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : ChatDeleteResponse(ok) {
    companion object
}

/**
 * Deletes a Message in a Chat
 *
 * @property channel the channel-id you want to delete
 * @property timestamp Timestamp of the message to be deleted.
 * @property asUser Pass true to delete the message as the authed user with chat:write:user scope. Bot users in this context are considered authed users. If unused or false, the message will be deleted with chat:write:bot scope.
 */
@JacksonDataClass
data class ChatDeleteRequest constructor(
    @JsonProperty("channel") val channel: String,
    @InstantToString @JsonProperty("ts") val timestamp: Instant,
    @JsonProperty("as_user") val asUser: Boolean = false
) {
    companion object
}
