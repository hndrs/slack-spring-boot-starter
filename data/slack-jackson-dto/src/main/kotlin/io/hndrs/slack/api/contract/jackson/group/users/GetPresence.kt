package io.hndrs.slack.api.contract.jackson.group.users

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.hndrs.slack.api.contract.jackson.UserPresence
import io.hndrs.slack.api.contract.jackson.util.JacksonDataClass

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

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property presence the presence information of the user
 * @property isOnline true when the user is currently online
 * @property autoAway auto_away will be true if slacks servers haven't detected any activity from the user in the last 30 minutes.
 * @property manualAway will be true if the user has manually set their presence to away.
 * @property connectionCount gives a count of total connections.
 * @property lastActivity indicates the last activity seen by our servers. If a user has no connected clients then this property will be absent
 */
data class SuccessfulGetPresenceResponse constructor(
    override val ok: Boolean,
    @JsonProperty("presence") val presence: UserPresence,
    @JsonProperty("online") val isOnline: Boolean?,
    @JsonProperty("auto_away") val autoAway: Boolean?,
    @JsonProperty("manual_away") val manualAway: Boolean?,
    @JsonProperty("connection_count") val connectionCount: Int?,
    @JsonProperty("last_activity") val lastActivity: String?
) : GetPresenceResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorGetPresenceResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : GetPresenceResponse(ok) {
    companion object
}

/**
 * Gets user presence information.
 *
 * @property user User to get presence info on. Defaults to the authed user.
 */
data class GetPresenceRequest(@JsonProperty("user") val user: String) {

    fun toRequestMap() = mutableMapOf("user" to user)

    companion object
}
