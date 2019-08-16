package com.kreait.slack.api.contract.jackson.group.users

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import com.kreait.slack.api.contract.jackson.UserPresence

/**
 * https://api.slack.com/methods/users.getPresence
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulGetPresenceResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorGetPresenceResponse::class, name = "false")
)

@JacksonDataClass
sealed class GetPresenceResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulGetPresenceResponse constructor(override val ok: Boolean,
                                                     @JsonProperty("presence") val presence: UserPresence,
                                                     @JsonProperty("online") val online: Boolean?,
                                                     @JsonProperty("auto_away") val autoAway: Boolean?,
                                                     @JsonProperty("manual_away") val manualAway: Boolean?,
                                                     @JsonProperty("connection_count") val connectionCount: Int?,
                                                     @JsonProperty("last_activity") val lastActivity: String?)
    : GetPresenceResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorGetPresenceResponse constructor(override val ok: Boolean,
                                                @JsonProperty("error") val error: String)
    : GetPresenceResponse(ok) {
    companion object
}

data class GetPresenceRequest(@JsonProperty("user") val user: String) {
    companion object

    fun toRequestMap() = mutableMapOf("user" to user)
}
