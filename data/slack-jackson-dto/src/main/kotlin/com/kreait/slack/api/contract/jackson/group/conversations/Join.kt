package com.kreait.slack.api.contract.jackson.group.conversations

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.common.ResponseMetadata
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "ok",
    visible = true
)
@JsonSubTypes(
    JsonSubTypes.Type(value = SuccessfulConversationJoinResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorConversationJoinResponse::class, name = "false")
)

@JacksonDataClass
sealed class ConversationJoinResponse(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property channel the channel-object after joining it
 * @property warning additional non-fatal warnings
 * @property responseMetadata responseMetadata, used for paging
 */
data class SuccessfulConversationJoinResponse(
    override val ok: Boolean,
    @JsonProperty("channel") val channel: Channel,
    @JsonProperty("warning") val warning: String?,
    @JsonProperty("response_metadata") val responseMetadata: ResponseMetadata?
) : ConversationJoinResponse(ok) {
    companion object

    @JacksonDataClass
    data class Channel(@JsonProperty("id") val id: String) {
        companion object
    }

}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorConversationJoinResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : ConversationJoinResponse(ok) {
    companion object
}

/**
 * Joins a channel
 *
 * @property channel the channel-id you want to join
 */
data class ConversationJoinRequest(@JsonProperty("channel") val channel: String) {
    companion object
}
