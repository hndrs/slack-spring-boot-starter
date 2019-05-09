package io.olaph.slack.dto.jackson.group.users

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.olaph.slack.dto.jackson.JacksonDataClass

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

    /*fun photoToFormData() {
        val formData: MutableMap<String, String> = mutableMapOf()
        formData["Accept-Charset"] = "UTF-8"
        formData["Connection"] = "Keep-Alive"
        formData["Cache-Control"] = "no-cache"
        formData["Content-Disposition"] = "multipart/form-data; name='image';"
        image.endsWith(".gif").apply { formData["Content-Type"] = "image/gif" }
        image.endsWith(".jpg").apply { formData["Content-Type"] = "image/jpeg" }
        image.endsWith(".jpeg").apply { formData["Content-Type"] = "image/jpeg" }
        image.endsWith(".png").apply { formData["Content-Type"] = "image/png" }
    }*/


}

