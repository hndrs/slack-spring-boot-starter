package io.hndrs.slack.api.contract.jackson.group.auth

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.hndrs.slack.api.contract.jackson.util.JacksonDataClass


@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "ok",
    visible = true
)
@JsonSubTypes(
    JsonSubTypes.Type(value = SuccessfulAuthRevokeResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorAuthRevokeResponse::class, name = "false")
)
@JacksonDataClass
sealed class AuthRevokeResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property isRevoked true if token was revoked
 */
@JacksonDataClass
data class SuccessfulAuthRevokeResponse constructor(
    override val ok: Boolean,
    @JsonProperty("revoked") val isRevoked: Boolean
) : AuthRevokeResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorAuthRevokeResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : AuthRevokeResponse(ok) {
    companion object
}


/**
 * Revokes a token.
 *
 * @property test setting test = true triggers a testing mode where the specified token will not actually be revoked
 * @see [Slack Api Method](https://api.slack.com/methods/auth.revoke)
 */
data class AuthRevokeRequest constructor(private val test: Boolean?) {
    
    fun toRequestMap(): MutableMap<String, String> {
        val requestMap = mutableMapOf<String, String>()
        test?.let { requestMap.put("test", test.toString()) }
        return requestMap
    }

    companion object
}
