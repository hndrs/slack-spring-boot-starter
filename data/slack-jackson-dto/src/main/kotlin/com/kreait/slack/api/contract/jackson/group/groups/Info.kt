package com.kreait.slack.api.contract.jackson.group.groups

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.common.types.Group
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulGroupsInfoResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorGroupsInfoResponse::class, name = "false")
)

@JacksonDataClass
sealed class GroupsInfoResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * [SlackDoc](https://api.slack.com/methods/groups.info)
 */
data class SuccessfulGroupsInfoResponse(
        override val ok: Boolean,
        @JsonProperty("group") val group: Group) : GroupsInfoResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/groups.info)
 */
data class ErrorGroupsInfoResponse constructor(
        override val ok: Boolean,
        @JsonProperty("error") val error: String
) : GroupsInfoResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/groups.info)
 */
data class GroupsInfoRequest(@JsonProperty("channel") val channelId: String,
                             @JsonProperty("include_locale") val includeLocale: Boolean? = null) {
    companion object
}
