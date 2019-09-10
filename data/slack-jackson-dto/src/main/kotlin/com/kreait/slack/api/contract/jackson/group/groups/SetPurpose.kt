package com.kreait.slack.api.contract.jackson.group.groups

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulGroupsSetPurposeResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorGroupsSetPurposeResponse::class, name = "false")
)

@JacksonDataClass
sealed class GroupsSetPurposeResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property newPurpose the new purpose
 */
data class SuccessfulGroupsSetPurposeResponse(
        override val ok: Boolean,
        @JsonProperty("purpose") val newPurpose: String) : GroupsSetPurposeResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
data class ErrorGroupsSetPurposeResponse constructor(
        override val ok: Boolean,
        @JsonProperty("error") val error: String
) : GroupsSetPurposeResponse(ok) {
    companion object
}

/**
 * Sets the purpose of a Group
 *
 * @property channelId the channel-id of the group you want to set the purpose for
 * @property purpose the new purpose
 */
data class GroupsSetPurposeRequest(@JsonProperty("channel") val channelId: String,
                                   @JsonProperty("purpose") val purpose: String) {
    companion object
}
