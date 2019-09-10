package com.kreait.slack.api.contract.jackson.group.im

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.util.InstantToInt
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import java.time.Instant

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)

@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulImListResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorImListResponse::class, name = "false")
)

@JacksonDataClass
sealed class ImListResponse constructor(@JsonProperty(value = "ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property ims list of direct message channels
 * @property responseMetadata metadata used for paging
 */
@JacksonDataClass
data class SuccessfulImListResponse constructor(override val ok: Boolean,
                                                @JsonProperty("ims") val ims: List<Im>,
                                                @JsonProperty("response_metadata") val responseMetadata: ResponseMetadata)
    : ImListResponse(ok) {
    companion object
}

data class ErrorImListResponse constructor(override val ok: Boolean,
                                           val error: String)
    : ImListResponse(ok) {
    companion object
}

data class Im(@InstantToInt @JsonProperty("created") val createdAt: Instant,
              @JsonProperty("id") val id: String,
              @JsonProperty("is_im") val isIm: Boolean,
              @JsonProperty("is_org_shared") val isOrgShared: Boolean,
              @JsonProperty("is_user_deleted") val isUserDeleted: Boolean,
              @JsonProperty("user") val user: String) {
    companion object
}

data class ResponseMetadata(@JsonProperty("next_cursor") val nextCursor: String) {
    companion object
}

/**
 * Lists all available Im-Channels
 *
 * @property cursor paging cursor
 * @property limit the limit of im-channels to fetch
 */
data class ImListRequest constructor(val cursor: String?,
                                     val limit: String?) {
    companion object {}

    fun toRequestMap(): MutableMap<String, String> {
        val requestMap = mutableMapOf<String, String>()
        cursor?.let { requestMap.put("cursor", it) }
        limit?.let { requestMap.put("limit", it) }
        return requestMap
    }
}
