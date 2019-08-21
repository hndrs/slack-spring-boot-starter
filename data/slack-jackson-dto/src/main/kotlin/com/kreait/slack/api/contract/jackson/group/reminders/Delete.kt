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
        JsonSubTypes.Type(value = SuccessfulRemindersDeleteResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorRemindersDeleteResponse::class, name = "false")
)

@JacksonDataClass
sealed class RemindersDeleteResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * [SlackDoc](https://api.slack.com/methods/reminders.delete)
 */
data class SuccessfulRemindersDeleteResponse(
        override val ok: Boolean) : RemindersDeleteResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/reminders.delete)
 */
data class ErrorRemindersDeleteResponse constructor(
        override val ok: Boolean,
        @JsonProperty("error") val error: String
) : RemindersDeleteResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/reminders.delete)
 */
data class RemindersDeleteRequest(@JsonProperty("reminder") val reminderId: String) {
    companion object
}
