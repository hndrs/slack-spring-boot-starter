package com.kreait.slack.api.contract.jackson.group.team

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.JacksonDataClass


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulProfileResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorProfileResponse::class, name = "false")
)
@JacksonDataClass
sealed class ProfileResponse(
        @JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulProfileResponse(
        override val ok: Boolean,
        @JsonProperty("profile") val members: TeamProfile) : ProfileResponse(ok) {
    companion object
}

data class TeamProfile(
        @JsonProperty("fields")
        val fields: List<Field>) {
    companion object {}

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
}


@JacksonDataClass
data class ErrorProfileResponse constructor(override val ok: Boolean,
                                            @JsonProperty("error") val error: String)
    : ProfileResponse(ok) {
    companion object
}

@JacksonDataClass
data class ProfileRequest(@JsonProperty("visibility") val visibility: TeamVisibility?) {

    companion object {}

    fun toRequestMap(): Map<String, String> {
        val map = mutableMapOf<String, String>()
        visibility?.let { map.put("visibility", it.value) }
        return map
    }
}

enum class TeamVisibility(val value: String) {
    ALL("all"),
    VISIBLE("visible"),
    HIDDEN("is_hidden")
}
