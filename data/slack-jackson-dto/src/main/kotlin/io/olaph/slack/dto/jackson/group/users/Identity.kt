package io.olaph.slack.dto.jackson.group.users

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.olaph.slack.dto.jackson.JacksonDataClass
import io.olaph.slack.dto.jackson.common.types.Identity

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulUsersIdentityResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorUsersIdentityResponse::class, name = "false")
)
@JacksonDataClass
sealed class UsersIdentityResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulUsersIdentityResponse constructor(override val ok: Boolean,
                                                       @JsonProperty("user") val user: Identity) : UsersIdentityResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorUsersIdentityResponse constructor(override val ok: Boolean,
                                                  @JsonProperty("error") val error: String)
    : UsersIdentityResponse(ok) {
    companion object
}