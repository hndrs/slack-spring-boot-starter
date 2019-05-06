package io.olaph.slack.dto.jackson.group.team

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.olaph.slack.dto.jackson.JacksonDataClass


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulTeamGetProfileResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorTeamGetProfileResponse::class, name = "false")
)
@JacksonDataClass
sealed class TeamGetProfileResponse(
        @JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulTeamGetProfileResponse(
        override val ok: Boolean,
        @JsonProperty("profile") val members: TeamProfile) : TeamGetProfileResponse(ok) {
    companion object
}

data class TeamProfile(
        @JsonProperty("fields")
        val fields: List<Field>) {
    companion object
}

data class Field(
        @JsonProperty("hint") val hint: String,
        @JsonProperty("id") val id: String,
        @JsonProperty("is_hidden") val isHidden: Int,
        @JsonProperty("label") val label: String,
        @JsonProperty("options") val options: Any?,
        @JsonProperty("ordering") val ordering: Int,
        @JsonProperty("possible_values") val possibleValues: Any?,
        @JsonProperty("type") val type: String) {
    companion object
}

@JacksonDataClass
data class ErrorTeamGetProfileResponse constructor(override val ok: Boolean,
                                                   @JsonProperty("error") val error: String)
    : TeamGetProfileResponse(ok) {
    companion object
}

@JacksonDataClass
data class TeamGetProfileRequest(@JsonProperty("visibility") val visibility: String?) {
    companion object {}

    fun toRequestMap(): Map<String, String> {
        val map = mutableMapOf<String, String>()
        visibility?.let { map.put("visibility", it) }
        return map
    }
}