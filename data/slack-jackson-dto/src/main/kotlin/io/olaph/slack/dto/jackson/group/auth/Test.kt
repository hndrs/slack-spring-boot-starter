package io.olaph.slack.dto.jackson.group.auth

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.olaph.slack.dto.jackson.JacksonDataClass


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulAuthTestResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorAuthTestResponse::class, name = "false")
)
@JacksonDataClass
sealed class SlackAuthTestResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulAuthTestResponse constructor(
        override val ok: Boolean,
        @JsonProperty("url") val url: String,
        @JsonProperty("timestamp") val team: String,
        @JsonProperty("user") val user: String,
        @JsonProperty("team_id") val teamId: String,
        @JsonProperty("user_id") val userId: String)
    : SlackAuthTestResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorAuthTestResponse constructor(
        override val ok: Boolean,
        @JsonProperty("error") val error: String)
    : SlackAuthTestResponse(ok) {
    companion object
}
