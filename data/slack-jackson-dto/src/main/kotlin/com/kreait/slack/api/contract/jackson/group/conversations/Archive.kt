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
    JsonSubTypes.Type(value = SuccessfulConversationArchiveResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorConversationArchiveResponse::class, name = "false")
)

@JacksonDataClass
sealed class ConversationArchiveResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 */
data class SuccessfulConversationArchiveResponse(
    override val ok: Boolean
) : ConversationArchiveResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
data class ErrorConversationArchiveResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : ConversationArchiveResponse(ok) {
    companion object
}

/**
 * This method archives a conversation. Not all types of conversations can be archived.
 *
 * @property channel the channel id you want to archive
 */
data class ConversationArchiveRequest(@JsonProperty("channel") val channel: String) {
    companion object
}
