package com.kreait.slack.api.contract.jackson.group.reminders

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.group.reminders.common.Reminder
import com.kreait.slack.api.contract.jackson.util.InstantToInt
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import java.time.Instant

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulRemindersAddResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorRemindersAddResponse::class, name = "false")
)

@JacksonDataClass
sealed class RemindersAddResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * [SlackDoc](https://api.slack.com/methods/reminders.Add)
 */
data class SuccessfulRemindersAddResponse(
        override val ok: Boolean,
        @JsonProperty("reminder") val reminder: Reminder) : RemindersAddResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/reminders.Add)
 */
data class ErrorRemindersAddResponse constructor(
        override val ok: Boolean,
        @JsonProperty("error") val error: String
) : RemindersAddResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/reminders.Add)
 */
data class RemindersAddRequest(@JsonProperty("text") val text: String,
                               @InstantToInt @JsonProperty("time") val triggerTimestamp: Instant, //time when the reminder should trigger
                               @JsonProperty("user") val userId: String? = null
) {
    companion object
}
