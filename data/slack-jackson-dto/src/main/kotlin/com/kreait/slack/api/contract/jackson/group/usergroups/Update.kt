package com.kreait.slack.api.contract.jackson.group.usergroups

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import com.kreait.slack.api.contract.jackson.common.types.Channel

/**
 * https://api.slack.com/methods/usergroups.update
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulUpdateResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorUpdateResponse::class, name = "false")
)

@JacksonDataClass
sealed class UpdateResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulUpdateResponse constructor(override val ok: Boolean,
                                                @JsonProperty("usergroup") val userGroup: UserGroup)
    : UpdateResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorUpdateResponse constructor(override val ok: Boolean,
                                           @JsonProperty("error") val error: String)
    : UpdateResponse(ok) {
    companion object
}

@JacksonDataClass
data class UpdateRequest constructor(@JsonProperty("usergroup") val usergroupId: String,
                                     @JsonProperty("channels") val channels: List<Channel>?,
                                     @JsonProperty("description") val description: String?,
                                     @JsonProperty("handle") val handle: String?,
                                     @JsonProperty("include_count") val includeCount: Boolean?,
                                     @JsonProperty("name") val name: String?) {
    companion object
}
