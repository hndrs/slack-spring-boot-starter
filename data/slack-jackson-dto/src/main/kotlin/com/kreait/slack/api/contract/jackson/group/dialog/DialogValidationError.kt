package com.kreait.slack.api.contract.jackson.group.dialog

import com.fasterxml.jackson.annotation.JsonProperty
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JacksonDataClass
data class DialogErrorResponse(
        @JsonProperty("errors") val errors: List<DialogValidationError>
)

@JacksonDataClass
data class DialogValidationError(
        @JsonProperty("name") val name: String,
        @JsonProperty("error") val error: String
)
