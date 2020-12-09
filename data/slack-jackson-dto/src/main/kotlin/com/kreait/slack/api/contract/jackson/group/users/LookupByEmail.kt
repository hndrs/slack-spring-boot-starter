package com.kreait.slack.api.contract.jackson.group.users

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.common.types.Member
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

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

/**
 * Success-response of this request.
 *
 * @property ok will be true
 */
data class SuccessfulLookupByEmailResponse constructor(
    override val ok: Boolean,
    @JsonProperty("user") val user: Member
) : LookupByEmailResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorLookupByEmailResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : LookupByEmailResponse(ok) {
    companion object
}

/**
 * Looks up a user by its email address
 *
 * @property email the email of the user you want to look up
 */
data class LookupByEmailRequest(private val email: String) {
    companion object {}

    fun toRequestMap(): MutableMap<String, String> = mutableMapOf("email" to email)
}

