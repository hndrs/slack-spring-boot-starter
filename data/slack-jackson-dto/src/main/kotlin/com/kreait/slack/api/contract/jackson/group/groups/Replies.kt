package com.kreait.slack.api.contract.jackson.group.groups

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.util.InstantToString
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import java.time.Instant

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulGroupsRepliesResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorGroupsRepliesResponse::class, name = "false")
)

@JacksonDataClass
sealed class GroupsRepliesResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * [SlackDoc](https://api.slack.com/methods/groups.replies)
 */
data class SuccessfulGroupsRepliesResponse(
        override val ok: Boolean,
        @JsonProperty("messages") val group: List<SuccessfulGroupsHistoryResponse.Message>) : GroupsRepliesResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/groups.replies)
 */
data class ErrorGroupsRepliesResponse constructor(
        override val ok: Boolean,
        @JsonProperty("error") val error: String
) : GroupsRepliesResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/groups.replies)
 */
data class GroupsRepliesRequest(@JsonProperty("channel") val channel: String,
                                @InstantToString @JsonProperty("thread_ts") val threadTimestamp: Instant) {
    companion object
}
