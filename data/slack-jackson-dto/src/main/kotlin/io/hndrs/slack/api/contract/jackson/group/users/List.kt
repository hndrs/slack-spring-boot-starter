package io.hndrs.slack.api.contract.jackson.group.users

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.hndrs.slack.api.contract.jackson.common.ResponseMetadata
import io.hndrs.slack.api.contract.jackson.common.types.Member
import io.hndrs.slack.api.contract.jackson.util.JacksonDataClass


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
    JsonSubTypes.Type(value = SuccessfulListResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorListResponse::class, name = "false")
)
@JacksonDataClass
sealed class ListResponse(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property members the list of users
 * @property cacheTs
 * @property responseMetadata metadata used for paging
 */
data class SuccessfulListResponse(
    override val ok: Boolean,
    @JsonProperty("members") val members: List<Member>,
    @JsonProperty("cache_ts") val cacheTs: Int,
    @JsonProperty("response_metadata") val responseMetadata: ResponseMetadata
) : ListResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorListResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : ListResponse(ok) {
    companion object
}

/**
 * Requests all users of the workspace
 *
 * @property includeLocale determines if the locale should be included in the response
 * @property limit the limit of users you want to retrieve
 * @property cursor the cursor used for paging
 */
data class ListRequest(
    private val includeLocale: Boolean? = null,
    private val limit: Int? = null,
    private val cursor: String? = null
) {

    fun toRequestMap(): MutableMap<String, String> {

        val requestMap = mutableMapOf<String, String>()
        cursor?.let { requestMap.put("cursor", it) }
        includeLocale?.let { requestMap.put("include_locale", it.toString()) }
        limit?.let { requestMap.put("limit", it.toString()) }
        return requestMap
    }


    companion object
}
