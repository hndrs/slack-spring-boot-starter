package com.kreait.slack.api.contract.jackson.common.messaging

import com.fasterxml.jackson.annotation.JsonProperty
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JacksonDataClass
data class Reaction(
    @JsonProperty("count") val count: Int?,
    @JsonProperty("name") val name: String?,
    @JsonProperty("users") val users: List<String>?
) {
    companion object
}
