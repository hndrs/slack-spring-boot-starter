package com.kreait.slack.api.contract.jackson.group.conversations

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.common.ResponseMetadata
import com.kreait.slack.api.contract.jackson.common.types.Channel
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulConversationsRenameResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorConversationsRenameResponse::class, name = "false")
)
@JacksonDataClass
sealed class ConversationsRenameResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property channel the renamed channel object
 */
data class SuccessfulConversationsRenameResponse(
        override val ok: Boolean,
        @JsonProperty("channel") val channel: Channel,
        @JsonProperty("response_metadata") val responseMetadata: ResponseMetadata)
    : ConversationsRenameResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorConversationsRenameResponse constructor(override val ok: Boolean,
                                                        @JsonProperty("error") val error: String)
    : ConversationsRenameResponse(ok) {
    companion object
}

/**
 * Renames a conversation.
 *
 * @property channel the channel-id of the channel you want to rename
 * @property name the new name for the channel
 */
data class ConversationsRenameRequest(@JsonProperty("channel") val channel: String,
                                      @JsonProperty("name") val name: String) {
    companion object
}
