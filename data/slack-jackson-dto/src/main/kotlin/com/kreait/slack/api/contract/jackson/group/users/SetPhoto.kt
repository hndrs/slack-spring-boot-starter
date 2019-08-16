package com.kreait.slack.api.contract.jackson.group.users

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import java.io.File
import java.nio.file.Files

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulSetPhotoResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorSetPhotoResponse::class, name = "false")
)

@JacksonDataClass
sealed class SetPhotoResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulSetPhotoResponse constructor(override val ok: Boolean)
    : SetPhotoResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorSetPhotoResponse constructor(override val ok: Boolean,
                                             val error: String)
    : SetPhotoResponse(ok) {
    companion object
}

@JacksonDataClass
data class SetPhotoRequest(val image: File,
                           val cropW: Int? = null,
                           val cropX: Int? = null,
                           val cropY: Int? = null) {
    companion object {}

    fun toMap(): Map<String, List<Any>> {
        val request = mutableMapOf<String, List<Any>>()
        request["image"] = listOf(Files.readAllBytes(image.toPath()))
        cropW?.let { request["crop_w"] = listOf(it.toString()) }
        cropX?.let { request["crop_x"] = listOf(it.toString()) }
        cropY?.let { request["crop_y"] = listOf(it.toString()) }

        return request
    }
}

