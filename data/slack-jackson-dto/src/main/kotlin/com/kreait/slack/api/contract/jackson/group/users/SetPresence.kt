package com.kreait.slack.api.contract.jackson.group.users

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.JacksonDataClass
import com.kreait.slack.api.contract.jackson.UserPresence

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulSetPresenceResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorSetPresenceResponse::class, name = "false")
)
@JacksonDataClass
sealed class SetPresenceResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulSetPresenceResponse constructor(override val ok: Boolean)
    : SetPresenceResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorSetPresenceResponse constructor(override val ok: Boolean,
                                                @JsonProperty("error") val error: String)
    : SetPresenceResponse(ok) {
    companion object
}

data class SetPresenceRequest(@JsonProperty("presence") val presence: UserPresence) {
    companion object
}
