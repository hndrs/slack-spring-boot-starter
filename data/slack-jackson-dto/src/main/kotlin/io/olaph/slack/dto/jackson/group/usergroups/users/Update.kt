package io.olaph.slack.dto.jackson.group.usergroups.users

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.olaph.slack.dto.jackson.JacksonDataClass
import io.olaph.slack.dto.jackson.common.types.Usergroup

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulUsergroupUsersUpdateResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorUsergroupUsersUpdateResponse::class, name = "false")
)

@JacksonDataClass
sealed class UsergroupsUsersUpdateResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulUsergroupUsersUpdateResponse constructor(override val ok: Boolean,
                                                              @JsonProperty("usergroup") val usergroup: Usergroup) :
        UsergroupsUsersUpdateResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorUsergroupUsersUpdateResponse constructor(override val ok: Boolean,
                                                         @JsonProperty("error") val error: String)
    : UsergroupsUsersUpdateResponse(ok) {
    companion object
}

@JacksonDataClass
data class SlackUsergroupUsersUpdateRequest(@JsonProperty("usergroup") val usergroup: String,
                                       @JsonProperty("users") val users: List<String>,
                                       @JsonProperty("include_count") val includeCount: Int?) {
    companion object
}