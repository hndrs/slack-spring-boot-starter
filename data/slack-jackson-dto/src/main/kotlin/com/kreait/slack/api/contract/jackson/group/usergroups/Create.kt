package com.kreait.slack.api.contract.jackson.group.usergroups

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.common.types.Channel
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

/**
 * https://api.slack.com/methods/usergroups.create
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulCreateResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorCreateResponse::class, name = "false")
)

@JacksonDataClass
sealed class CreateResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property userGroup the created usergroup
 */
@JacksonDataClass
data class SuccessfulCreateResponse constructor(override val ok: Boolean,
                                                @JsonProperty("usergroup") val userGroup: UserGroup)
    : CreateResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorCreateResponse constructor(override val ok: Boolean,
                                           @JsonProperty("error") val error: String)
    : CreateResponse(ok) {
    companion object
}

/**
 * Create a User Group
 *
 * @property name the name of the group you want to create
 * @property channels A comma separated string of encoded channel IDs for which the User Group uses as a default.
 * @property description A short description of the User Group.
 * @property handle A mention handle. Must be unique among channels, users and User Groups. e.g. "marketing"
 * @property includeCount Include the number of users in each User Group.
 */
@JacksonDataClass
data class CreateRequest constructor(@JsonProperty("name") val name: String,
                                     @JsonProperty("channels") val channels: List<Channel>?,
                                     @JsonProperty("description") val description: String?,
                                     @JsonProperty("handle") val handle: String?,
                                     @JsonProperty("include_count") val includeCount: Boolean?) {
    companion object
}
