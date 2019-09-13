package com.kreait.slack.api.contract.jackson.common

import com.fasterxml.jackson.annotation.JsonProperty
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass


/**
 * Metadata for responses
 *
 * @property nextCursor parameter used for paging
 * @property warnings warnings retreived by the api
 */
@JacksonDataClass
data class ResponseMetadata(@JsonProperty("next_cursor") val nextCursor: String? = null,
                            @JsonProperty("warnings") val warnings: List<String>? = null) {
    companion object

    fun hasNext() = !nextCursor.isNullOrBlank()
}
