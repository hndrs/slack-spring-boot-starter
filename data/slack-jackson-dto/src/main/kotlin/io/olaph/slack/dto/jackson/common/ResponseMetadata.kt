package io.olaph.slack.dto.jackson.common

import com.fasterxml.jackson.annotation.JsonProperty
import io.olaph.slack.dto.jackson.JacksonDataClass

@JacksonDataClass
data class ResponseMetadata(@JsonProperty("next_cursor") val nextCursor: String?) {
    companion object
}
