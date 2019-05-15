package io.olaph.slack.dto.jackson.group.users

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.olaph.slack.dto.jackson.JacksonDataClass
import org.springframework.core.io.Resource
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

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
                                                  private val error: String)
    : UsersSetPhotoResponse(ok) {
    companion object
}

@JacksonDataClass
data class UsersSetPhotoRequest(private val image: String,
                                private val cropW: Int?,
                                private val cropX: Int?,
                                private val cropY: Int?) {
    companion object{}

    fun toRequestMap(): MutableMap<String, String> {

        val request: MutableMap<String, String> = mutableMapOf()
        // imagePath
        request["image"] = image
        cropW?.let { request.put("crop_w", it.toString()) }
        cropX?.let { request.put("crop_x", it.toString()) }
        cropY?.let { request.put("crop_y", it.toString()) }

        return request
    }
}

