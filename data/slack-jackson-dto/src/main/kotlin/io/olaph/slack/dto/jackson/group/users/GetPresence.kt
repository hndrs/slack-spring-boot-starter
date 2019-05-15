package io.olaph.slack.dto.jackson.group.users

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.olaph.slack.dto.jackson.JacksonDataClass
import io.olaph.slack.dto.jackson.UserPresence

/**
 * https://api.slack.com/methods/users.getPresence
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulUsersGetPresenceResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorUsersGetPresenceResponse::class, name = "false")
)

@JacksonDataClass
sealed class UsersGetPresenceResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulUsersGetPresenceResponse constructor(override val ok: Boolean,
                                                          @JsonProperty("presence") val presence: UserPresence,
                                                          @JsonProperty("online") val online: Boolean?,
                                                          @JsonProperty("auto_away") val autoAway: Boolean?,
                                                          @JsonProperty("manual_away") val manualAway: Boolean?,
                                                          @JsonProperty("connection_count") val connectionCount: Int?,
                                                          @JsonProperty("last_activity") val lastActivity: String?)
    : UsersGetPresenceResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorUsersGetPresenceResponse constructor(override val ok: Boolean,
                                                     @JsonProperty("error") val error: String)
    : UsersGetPresenceResponse(ok) {
    companion object
}

data class UsersGetPresenceRequest(@JsonProperty("user") val user: String) {
    companion object

    fun toRequestMap() = mutableMapOf("user" to user)
}