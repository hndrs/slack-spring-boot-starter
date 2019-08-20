package com.kreait.slack.api.contract.jackson.group.groups

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.common.types.Topic
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulGroupsSetTopicResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorGroupsSetTopicResponse::class, name = "false")
)

@JacksonDataClass
sealed class GroupsSetTopicResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * [SlackDoc](https://api.slack.com/methods/groups.setTopic)
 */
data class SuccessfulGroupsSetTopicResponse(
        override val ok: Boolean,
        @JsonProperty("topic") val topic: Topic) : GroupsSetTopicResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/groups.setTopic)
 */
data class ErrorGroupsSetTopicResponse constructor(
        override val ok: Boolean,
        @JsonProperty("error") val error: String
) : GroupsSetTopicResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/groups.setTopic)
 */
data class GroupsSetTopicRequest(@JsonProperty("channel") val channelId: String,
                                 @JsonProperty("topic") val topic: Topic) {
    companion object
}
