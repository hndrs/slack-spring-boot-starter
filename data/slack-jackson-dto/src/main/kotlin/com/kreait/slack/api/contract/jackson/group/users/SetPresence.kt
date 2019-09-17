package com.kreait.slack.api.contract.jackson.group.users

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.UserPresence
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulSetPresenceResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorSetPresenceResponse::class, name = "false")
)
@JacksonDataClass
sealed class SetPresenceResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 */
data class SuccessfulSetPresenceResponse constructor(override val ok: Boolean)
    : SetPresenceResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorSetPresenceResponse constructor(override val ok: Boolean,
                                                @JsonProperty("error") val error: String)
    : SetPresenceResponse(ok) {
    companion object
}

/**
 * Manually sets user presence.
 *
 * @property presence the presence you want to set
 */
data class SetPresenceRequest(@JsonProperty("presence") val presence: UserPresence) {
    companion object
}
