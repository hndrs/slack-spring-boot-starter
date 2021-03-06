package io.hndrs.slack.api.contract.jackson.group.interactive_component

import com.fasterxml.jackson.annotation.JsonProperty
import io.hndrs.slack.api.contract.jackson.util.JacksonDataClass

@JacksonDataClass
data class InteractiveComponentMessageResponse(
    @JsonProperty("title") val token: String? = null,
    @JsonProperty("delete_original") val deleteOriginal: Boolean = false,
    @JsonProperty("replace_original") val replaceOriginal: Boolean = false
)
