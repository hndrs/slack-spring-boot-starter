package io.hndrs.slack.api.contract.jackson.group.conversations

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.hndrs.slack.api.contract.jackson.util.JacksonDataClass

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "ok",
    visible = true
)
@JsonSubTypes(
    JsonSubTypes.Type(value = SuccessfulConversationKickResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorConversationKickResponse::class, name = "false")
)
@JacksonDataClass
sealed class ConversationsKickResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 */
data class SuccessfulConversationKickResponse(
    override val ok: Boolean
) : ConversationsKickResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorConversationKickResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : ConversationsKickResponse(ok) {
    companion object
}

/**
 * Removes a user from a conversation
 *
 * @property channel the channel-id of the channel you want to remove the user from
 * @property user the user-id of the user you want to remove
 */
data class ConversationsKickRequest(
    @JsonProperty("channel") val channel: String,
    @JsonProperty("user") val user: String
) {
    companion object
}
