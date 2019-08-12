package com.kreait.slack.api.contract.jackson.group.usergroups

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.JacksonDataClass
import com.kreait.slack.api.contract.jackson.common.types.Channel

/**
 * https://api.slack.com/methods/usergroups.update
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulUsergroupsUpdateResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorUsergroupsUpdateResponse::class, name = "false")
)

@JacksonDataClass
sealed class UsergroupsUpdateResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulUsergroupsUpdateResponse constructor(override val ok: Boolean,
                                                          @JsonProperty("usergroup") val usergroup: Usergroup)
    : UsergroupsUpdateResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorUsergroupsUpdateResponse constructor(override val ok: Boolean,
                                                     @JsonProperty("error") val error: String)
    : UsergroupsUpdateResponse(ok) {
    companion object
}

@JacksonDataClass
data class SlackUsergroupsUpdateRequest constructor(@JsonProperty("usergroup") val usergroupId: String,
                                                    @JsonProperty("channels") val channels: List<Channel>?,
                                                    @JsonProperty("description") val description: String?,
                                                    @JsonProperty("handle") val handle: String?,
                                                    @JsonProperty("include_count") val includeCount: Boolean?,
                                                    @JsonProperty("name") val name: String?) {
    companion object
}
