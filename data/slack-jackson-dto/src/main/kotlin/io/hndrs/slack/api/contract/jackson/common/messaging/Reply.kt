package io.hndrs.slack.api.contract.jackson.common.messaging

import com.fasterxml.jackson.annotation.JsonProperty
import io.hndrs.slack.api.contract.jackson.util.InstantToString
import io.hndrs.slack.api.contract.jackson.util.JacksonDataClass
import java.time.Instant

@JacksonDataClass
data class Reply(
    @JsonProperty("user") val userId: String,
    @InstantToString @JsonProperty("ts") val timestamp: Instant
)
