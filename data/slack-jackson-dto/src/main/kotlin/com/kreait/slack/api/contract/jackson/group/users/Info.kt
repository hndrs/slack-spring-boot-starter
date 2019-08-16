package com.kreait.slack.api.contract.jackson.group.users

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import com.kreait.slack.api.contract.jackson.common.types.Member

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulInfoResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorInfoResponse::class, name = "false")
)
@JacksonDataClass
sealed class InfoResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulInfoResponse constructor(override val ok: Boolean,
                                              @JsonProperty("user") val user: Member)
    : InfoResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorInfoResponse constructor(override val ok: Boolean,
                                         @JsonProperty("error") val error: String)
    : InfoResponse(ok) {
    companion object
}

data class InfoRequest(private val userId: String, private val includeLocale: Boolean? = null) {

    companion object

    fun toRequestMap(): MutableMap<String, String> {
        val requestMap = mutableMapOf("user" to userId)
        includeLocale?.let { requestMap.put("include_locale", it.toString()) }
        return requestMap
    }
}
