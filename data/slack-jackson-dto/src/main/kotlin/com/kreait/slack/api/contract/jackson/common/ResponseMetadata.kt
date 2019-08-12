package com.kreait.slack.api.contract.jackson.common

import com.fasterxml.jackson.annotation.JsonProperty
import com.kreait.slack.api.contract.jackson.JacksonDataClass

@JacksonDataClass
data class ResponseMetadata(@JsonProperty("next_cursor") val nextCursor: String? = null,
                            @JsonProperty("warnings") val warnings: List<String>? = null) {
    companion object
}
