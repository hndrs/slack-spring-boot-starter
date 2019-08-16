package com.kreait.slack.api.contract.jackson.group.users

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import com.kreait.slack.api.contract.jackson.common.types.Identity

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulIdentityResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorIdentityResponse::class, name = "false")
)
@JacksonDataClass
sealed class IdentityResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulIdentityResponse constructor(override val ok: Boolean,
                                                  @JsonProperty("user") val user: Identity) : IdentityResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorIdentityResponse constructor(override val ok: Boolean,
                                             @JsonProperty("error") val error: String)
    : IdentityResponse(ok) {
    companion object
}
