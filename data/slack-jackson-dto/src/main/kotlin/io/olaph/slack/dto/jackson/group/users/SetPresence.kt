package io.olaph.slack.dto.jackson.group.users

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.olaph.slack.dto.jackson.JacksonDataClass
import io.olaph.slack.dto.jackson.UserPresence

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulUsersSetPresenceResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorUsersSetPresenceResponse::class, name = "false")
)
@JacksonDataClass
sealed class UsersSetPresenceResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulUsersSetPresenceResponse constructor(override val ok: Boolean)
    : UsersSetPresenceResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorUsersSetPresenceResponse constructor(override val ok: Boolean,
                                                     @JsonProperty("error") val error: String)
    : UsersSetPresenceResponse(ok) {
    companion object
}

data class SlackUsersSetPresenceRequest(@JsonProperty("presence") val presence: UserPresence) {
    companion object
}