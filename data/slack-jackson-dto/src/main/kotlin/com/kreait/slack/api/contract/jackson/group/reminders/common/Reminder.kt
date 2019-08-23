package com.kreait.slack.api.contract.jackson.group.reminders.common

import com.fasterxml.jackson.annotation.JsonProperty

data class Reminder(
        @JsonProperty("complete_ts") val completeTimestamp: Int?,
        @JsonProperty("creator") val creator: String?,
        @JsonProperty("id") val id: String?,
        @JsonProperty("recurring") val recurring: Boolean?,
        @JsonProperty("text") val text: String?,
        @JsonProperty("time") val time: Int?,
        @JsonProperty("user") val userId: String?
) {
    companion object
}