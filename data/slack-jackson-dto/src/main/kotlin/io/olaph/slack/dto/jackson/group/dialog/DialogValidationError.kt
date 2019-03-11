package io.olaph.slack.dto.jackson.group.dialog

import com.fasterxml.jackson.annotation.JsonProperty
import io.olaph.slack.dto.jackson.JacksonDataClass

@JacksonDataClass
data class DialogErrorResponse(
        @JsonProperty("errors") val errors: List<DialogValidationError>
)

@JacksonDataClass
data class DialogValidationError(
        @JsonProperty("name") val name: String,
        @JsonProperty("error") val error: String
)
