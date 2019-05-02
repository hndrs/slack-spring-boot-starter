package io.olaph.slack.dto.jackson.group.users

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.olaph.slack.dto.jackson.JacksonDataClass

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

data class Identity(val user: User, val team: Team) {
    data class User(val name: String,
                    val id: String,
                    val email: String?,
                    val image24: String?,
                    val image32: String?,
                    val image48: String?,
                    val image72: String?,
                    val image192: String?) {
        companion object
    }

    data class Team(val id: String,
                    val name: String?) {
        companion object
    }
}