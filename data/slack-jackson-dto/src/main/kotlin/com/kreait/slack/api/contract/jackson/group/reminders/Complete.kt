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
        JsonSubTypes.Type(value = SuccessfulRemindersCompleteResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorRemindersCompleteResponse::class, name = "false")
)

@JacksonDataClass
sealed class RemindersCompleteResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * [SlackDoc](https://api.slack.com/methods/reminders.Complete)
 */
data class SuccessfulRemindersCompleteResponse(
        override val ok: Boolean) : RemindersCompleteResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/reminders.Complete)
 */
data class ErrorRemindersCompleteResponse constructor(
        override val ok: Boolean,
        @JsonProperty("error") val error: String
) : RemindersCompleteResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/reminders.Complete)
 */
data class RemindersCompleteRequest(@JsonProperty("reminder") val reminderId: String) {
    companion object
}
