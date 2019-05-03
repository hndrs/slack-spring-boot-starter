package io.olaph.slack.dto.jackson.group.im

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.olaph.slack.dto.jackson.JacksonDataClass

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)

@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulImListResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorImListResponse::class, name = "false")
)

@JacksonDataClass
sealed class SlackImListResponse constructor(@JsonProperty(value = "ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulImListResponse constructor(override val ok: Boolean,
                                                val ims: List<Im>,
                                                val responseMetadata: ResponseMetadata)
    : SlackImListResponse(ok) {
    companion object
}

data class ErrorImListResponse constructor(override val ok: Boolean,
                                           val error: String)
    : SlackImListResponse(ok) {
    companion object
}

data class Im(val created: Int,
              val id: String,
              val isIm: Boolean,
              val isOrgShared: Boolean,
              val isUserDeleted: Boolean,
              val user: String) {
    companion object {

    }
}

data class ResponseMetadata(val nextCursor: String) {
    companion object
}

data class SlackImListRequest constructor(val cursor: String?,
                                          val limit: String?) {
    companion object {}

    fun toRequestMap(): MutableMap<String, String> {
        val requestMap = mutableMapOf<String, String>()
        cursor?.let { requestMap.put("cursor", it) }
        limit?.let { requestMap.put("limit", it) }
        return requestMap
    }
}