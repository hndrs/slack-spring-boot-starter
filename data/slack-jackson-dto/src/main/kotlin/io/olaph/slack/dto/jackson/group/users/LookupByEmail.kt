package io.olaph.slack.dto.jackson.group.users

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.olaph.slack.dto.jackson.JacksonDataClass

/**
 * https://api.slack.com/methods/users.lookupByEmail
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulUsersLookupByEmailResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorUsersLookupByEmailResponse::class, name = "false")
)

@JacksonDataClass
sealed class SlackLookupByEmailResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulUsersLookupByEmailResponse constructor(override val ok: Boolean,
                                                            @JsonProperty("user") val user: User)
    : SlackLookupByEmailResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorUsersLookupByEmailResponse constructor(override val ok: Boolean,
                                                       @JsonProperty("error") val error: String)
    : SlackLookupByEmailResponse(ok) {
    companion object
}

data class SlackUsersLookupByEmailRequest(private val email: String) {

    companion object {}

    fun toRequestMap(): MutableMap<String, String> = mutableMapOf("email" to email)

}

