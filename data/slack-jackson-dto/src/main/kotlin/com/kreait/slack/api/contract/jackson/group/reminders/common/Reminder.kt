package com.kreait.slack.api.contract.jackson.group.reminders.common

import com.fasterxml.jackson.annotation.JsonProperty
import com.kreait.slack.api.contract.jackson.util.InstantToInt
import java.time.Instant

data class Reminder(
        @InstantToInt @JsonProperty("complete_ts") val completeTimestamp: Instant?,
        @JsonProperty("creator") val createdBy: String,
        @JsonProperty("id") val id: String,
        @JsonProperty("recurring") val recurring: Boolean,
        @JsonProperty("text") val text: String,
        @InstantToInt @JsonProperty("time") val time: Instant?,
        @JsonProperty("user") val userId: String
) {
    companion object
}