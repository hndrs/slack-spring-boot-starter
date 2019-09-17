package com.kreait.slack.api.contract.jackson.group.users

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulDeletePhotoResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorDeletePhotoResponse::class, name = "false")
)
@JacksonDataClass
sealed class DeletePhotoResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 */
data class SuccessfulDeletePhotoResponse constructor(override val ok: Boolean)
    : DeletePhotoResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorDeletePhotoResponse constructor(override val ok: Boolean,
                                                @JsonProperty("error") val error: String)
    : DeletePhotoResponse(ok) {
    companion object
}
