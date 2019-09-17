package com.kreait.slack.api.contract.jackson.group.users

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.common.types.Identity
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulIdentityResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorIdentityResponse::class, name = "false")
)
@JacksonDataClass
sealed class IdentityResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property user the users identity
 */
data class SuccessfulIdentityResponse constructor(override val ok: Boolean,
                                                  @JsonProperty("user") val user: Identity) : IdentityResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorIdentityResponse constructor(override val ok: Boolean,
                                             @JsonProperty("error") val error: String)
    : IdentityResponse(ok) {
    companion object
}
