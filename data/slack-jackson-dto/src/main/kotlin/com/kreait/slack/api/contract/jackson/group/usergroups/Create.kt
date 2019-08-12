package com.kreait.slack.api.contract.jackson.group.usergroups

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.JacksonDataClass
import com.kreait.slack.api.contract.jackson.common.types.Channel

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

@JacksonDataClass
data class SuccessfulCreateResponse constructor(override val ok: Boolean,
                                                @JsonProperty("usergroup") val userGroup: UserGroup)
    : CreateResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorCreateResponse constructor(override val ok: Boolean,
                                           @JsonProperty("error") val error: String)
    : CreateResponse(ok) {
    companion object
}

@JacksonDataClass
data class CreateRequest constructor(@JsonProperty("name") val name: String,
                                     @JsonProperty("channels") val channels: List<Channel>?,
                                     @JsonProperty("description") val description: String?,
                                     @JsonProperty("handle") val handle: String?,
                                     @JsonProperty("include_count") val includeCount: Boolean?) {
    companion object
}
