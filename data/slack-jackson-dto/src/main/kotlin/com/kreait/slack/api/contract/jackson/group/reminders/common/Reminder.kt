package com.kreait.slack.api.contract.jackson.group.reminders.common

import com.fasterxml.jackson.annotation.JsonProperty
import com.kreait.slack.api.contract.jackson.util.InstantToInt
import java.time.Instant

/**
 * Reminder dto
 *
 * @property completeTimestamp the timestamp when this reminder is completed
 * @property createdBy the user id of the user that created the reminder
 * @property id the id of the reminder
 * @property isRecurring determines if the reminder is recurring
 * @property text the reminder-text
 * @property time the reminder-time
 * @property userId the user id that should be reminded
 */
data class Reminder(
        @InstantToInt @JsonProperty("complete_ts") val completeTimestamp: Instant?,
        @JsonProperty("creator") val createdBy: String,
        @JsonProperty("id") val id: String,
        @JsonProperty("recurring") val isRecurring: Boolean,
        @JsonProperty("text") val text: String,
        @InstantToInt @JsonProperty("time") val time: Instant?,
        @JsonProperty("user") val userId: String
) {
    companion object
}