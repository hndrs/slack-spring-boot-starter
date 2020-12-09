package com.kreait.slack.api.contract.jackson.group.conversations

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "ok",
    visible = true
)
@JsonSubTypes(
    JsonSubTypes.Type(value = SuccessfulConversationUnarchiveResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorConversationUnarchiveResponse::class, name = "false")
)
@JacksonDataClass
sealed class ConversationUnarchiveResponse constructor(@JsonProperty("ok") open val ok: Boolean)


/**
 * Success-response of this request.
 *
 * @property ok will be true
 */
data class SuccessfulConversationUnarchiveResponse(
    override val ok: Boolean
) : ConversationUnarchiveResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
data class ErrorConversationUnarchiveResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : ConversationUnarchiveResponse(ok) {
    companion object
}

/**
 * Reverses conversation archival.
 *
 * @property channel the channel-id for the channel you want to unarchive
 */
data class ConversationUnarchiveRequest(@JsonProperty("channel") val channel: String) {
    companion object
}
