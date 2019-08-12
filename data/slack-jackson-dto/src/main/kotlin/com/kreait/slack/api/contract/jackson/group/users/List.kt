package com.kreait.slack.api.contract.jackson.group.users

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.JacksonDataClass
import com.kreait.slack.api.contract.jackson.common.ResponseMetadata
import com.kreait.slack.api.contract.jackson.common.types.Member


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulListResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorListResponse::class, name = "false")
)
@JacksonDataClass
sealed class ListResponse(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulListResponse(
        override val ok: Boolean,
        @JsonProperty("members") val members: List<Member>,
        @JsonProperty("cache_ts") val cacheTs: Int,
        @JsonProperty("response_metadata") val responseMetadata: ResponseMetadata
) : ListResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorListResponse constructor(override val ok: Boolean,
                                         @JsonProperty("error") val error: String)
    : ListResponse(ok) {
    companion object
}

/**
 * DataClass that represents arguments as defined here https://api.slack.com/methods/users.list
 */
data class ListRequest(private val includeLocale: Boolean? = null,
                       private val limit: Int? = null,
                       private val includePresence: Boolean? = null,
                       private val cursor: String? = null) {

    fun toRequestMap(): MutableMap<String, String> {

        val requestMap = mutableMapOf<String, String>()
        cursor?.let { requestMap.put("cursor", it) }
        includeLocale?.let { requestMap.put("include_locale", it.toString()) }
        limit?.let { requestMap.put("limit", it.toString()) }
        return requestMap
    }

    companion object
}
