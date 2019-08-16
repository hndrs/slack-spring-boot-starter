package com.kreait.slack.api.contract.jackson.group.users

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import com.kreait.slack.api.contract.jackson.common.types.Member

/**
 * https://api.slack.com/methods/users.lookupByEmail
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulLookupByEmailResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorLookupByEmailResponse::class, name = "false")
)

@JacksonDataClass
sealed class LookupByEmailResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulLookupByEmailResponse constructor(override val ok: Boolean,
                                                       @JsonProperty("user") val user: Member)
    : LookupByEmailResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorLookupByEmailResponse constructor(override val ok: Boolean,
                                                  @JsonProperty("error") val error: String)
    : LookupByEmailResponse(ok) {
    companion object
}

data class LookupByEmailRequest(private val email: String) {
    companion object {}

    fun toRequestMap(): MutableMap<String, String> = mutableMapOf("email" to email)
}

