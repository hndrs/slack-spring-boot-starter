package com.kreait.slack.api.contract.jackson.common.types

import com.fasterxml.jackson.annotation.JsonProperty
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JacksonDataClass
data class Shares(
    @JsonProperty("public") val publicChannels: Map<String, List<ShareDetail>>,
    @JsonProperty("private") val privateChannels: Map<String, List<ShareDetail>>
)
