package com.kreait.slack.api.contract.jackson.group.users

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.JacksonDataClass
import org.springframework.core.io.FileSystemResource
import org.springframework.util.LinkedMultiValueMap
import java.io.File

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulUsersSetPhotoResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorUsersSetPhotoResponse::class, name = "false")
)

@JacksonDataClass
sealed class UsersSetPhotoResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulUsersSetPhotoResponse constructor(override val ok: Boolean)
    : UsersSetPhotoResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorUsersSetPhotoResponse constructor(override val ok: Boolean,
                                                  val error: String)
    : UsersSetPhotoResponse(ok) {
    companion object
}

@JacksonDataClass
data class UsersSetPhotoRequest(val image: File,
                                val cropW: Int? = null,
                                val cropX: Int? = null,
                                val cropY: Int? = null) {
    companion object {}

    fun toMultiValueMap(): LinkedMultiValueMap<String, Any> {
        val request: LinkedMultiValueMap<String, Any> = LinkedMultiValueMap()
        request.add("image", FileSystemResource(image))
        cropW?.let { request.add("crop_w", it.toString()) }
        cropX?.let { request.add("crop_x", it.toString()) }
        cropY?.let { request.add("crop_y", it.toString()) }

        return request
    }
}

