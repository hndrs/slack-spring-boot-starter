package com.kreait.slack.api.contract.jackson.group.groups

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.common.types.Message
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
    JsonSubTypes.Type(value = SuccessfulGroupsRepliesResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorGroupsRepliesResponse::class, name = "false")
)

@JacksonDataClass
sealed class GroupsRepliesResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property messages list of thread messages
 */
data class SuccessfulGroupsRepliesResponse(
    override val ok: Boolean,
    @JsonProperty("messages") val messages: List<Message>
) : GroupsRepliesResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
data class ErrorGroupsRepliesResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : GroupsRepliesResponse(ok) {
    companion object
}

/**
 * Returns the thread-messages of a thread in a group
 *
 * @property channel the channel-id of the channel that contains the thread
 * @property threadTimestamp the timestamp of the thread
 */
data class GroupsRepliesRequest(
    @JsonProperty("channel") val channel: String,
    @InstantToString @JsonProperty("thread_ts") val threadTimestamp: Instant
) {
    companion object
}
