package com.kreait.slack.api.contract.jackson.group.im

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.JacksonDataClass
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

data class Im(@JsonProperty("created") val createdAt: Instant,
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
