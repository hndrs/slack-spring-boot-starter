package com.kreait.slack.api.contract.jackson.group.auth

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.JacksonDataClass


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulAuthRevokeResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorAuthRevokeResponse::class, name = "false")
)
@JacksonDataClass
sealed class AuthRevokeResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulAuthRevokeResponse constructor(
        override val ok: Boolean,
        @JsonProperty("revoked") val isRevoked: Boolean)
    : AuthRevokeResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorAuthRevokeResponse constructor(
        override val ok: Boolean,
        @JsonProperty("error") val error: String)
    : AuthRevokeResponse(ok) {
    companion object
}


data class AuthRevokeRequest constructor(private val test: Boolean?) {

    companion object

    fun toRequestMap(): MutableMap<String, String> {
        val requestMap = mutableMapOf<String, String>()
        test?.let { requestMap.put("test", test.toString()) }
        return requestMap
    }
}
