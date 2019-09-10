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
 * Success-response of this request.
 *
 * @property ok will be true
 * @property group the information about the group
 */
data class SuccessfulGroupsInfoResponse(
        override val ok: Boolean,
        @JsonProperty("group") val group: Group) : GroupsInfoResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
data class ErrorGroupsInfoResponse constructor(
        override val ok: Boolean,
        @JsonProperty("error") val error: String
) : GroupsInfoResponse(ok) {
    companion object
}

/**
 * Gets information about the group
 *
 * @property channelId the channel of which you want to request information about
 * @property includeLocale determines if the locale of the group should be included
 */
data class GroupsInfoRequest(@JsonProperty("channel") val channelId: String,
                             @JsonProperty("include_locale") val includeLocale: Boolean? = null) {
    companion object
}
