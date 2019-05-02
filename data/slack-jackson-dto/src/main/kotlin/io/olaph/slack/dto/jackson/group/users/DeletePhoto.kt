package io.olaph.slack.dto.jackson.group.users

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.olaph.slack.dto.jackson.JacksonDataClass


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulUsersDeletePhotoResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorUsersDeletePhotoResponse::class, name = "false")
)
@JacksonDataClass
sealed class UsersDeletePhotoResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulUsersDeletePhotoResponse constructor(override val ok: Boolean)
    : UsersDeletePhotoResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorUsersDeletePhotoResponse constructor(override val ok: Boolean,
                                                     @JsonProperty("error") val error: String)
    : UsersDeletePhotoResponse(ok) {
    companion object
}