package com.kreait.slack.api.contract.jackson.group.reminders

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.group.reminders.common.Reminder
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulRemindersListResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorRemindersListResponse::class, name = "false")
)

@JacksonDataClass
sealed class RemindersListResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * [SlackDoc](https://api.slack.com/methods/reminders.List)
 */
data class SuccessfulRemindersListResponse(
        override val ok: Boolean,
        @JsonProperty("reminders") val reminders: List<Reminder>) : RemindersListResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/reminders.List)
 */
data class ErrorRemindersListResponse constructor(
        override val ok: Boolean,
        @JsonProperty("error") val error: String
) : RemindersListResponse(ok) {
    companion object
}

