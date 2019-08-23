package com.kreait.slack.api.contract.jackson.group.reminders

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulRemindersInfoResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorRemindersInfoResponse::class, name = "false")
)

@JacksonDataClass
sealed class RemindersInfoResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * [SlackDoc](https://api.slack.com/methods/reminders.Info)
 */
data class SuccessfulRemindersInfoResponse(
        override val ok: Boolean,
        @JsonProperty("reminder") val reminder: Reminder) : RemindersInfoResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/reminders.Info)
 */
data class ErrorRemindersInfoResponse constructor(
        override val ok: Boolean,
        @JsonProperty("error") val error: String
) : RemindersInfoResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/reminders.Info)
 */
data class RemindersInfoRequest(@JsonProperty("reminder") val reminderId: String) {
    companion object
}

data class Reminder(
        @JsonProperty("id") val id: String,
        @JsonProperty("complete_ts") val completeTimestamp: Int?,
        @JsonProperty("creator") val creator: String?,
        @JsonProperty("recurring") val recurring: Boolean?,
        @JsonProperty("text") val text: String?,
        @JsonProperty("time") val time: Int?,
        @JsonProperty("user") val user: String?
)