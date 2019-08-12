package com.kreait.slack.api.contract.jackson.group.users

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.JacksonDataClass
import com.kreait.slack.api.contract.jackson.common.types.Member

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulUsersInfoResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorUsersInfoResponse::class, name = "false")
)
@JacksonDataClass
sealed class SlackInfoResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulUsersInfoResponse constructor(override val ok: Boolean,
                                                   @JsonProperty("user") val user: Member)
    : SlackInfoResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorUsersInfoResponse constructor(override val ok: Boolean,
                                              @JsonProperty("error") val error: String)
    : SlackInfoResponse(ok) {
    companion object
}

data class SlackUserInfoRequest(private val userId: String, private val includeLocale: Boolean? = null) {

    companion object

    fun toRequestMap(): MutableMap<String, String> {
        val requestMap = mutableMapOf("user" to userId)
        includeLocale?.let { requestMap.put("include_locale", it.toString()) }
        return requestMap
    }
}
