package com.kreait.slack.api.contract.jackson.group.reminders

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.group.reminders.common.Reminder
import com.kreait.slack.api.contract.jackson.util.InstantToInt
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import java.time.Instant

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "ok",
    visible = true
)
@JsonSubTypes(
    JsonSubTypes.Type(value = SuccessfulRemindersAddResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorRemindersAddResponse::class, name = "false")
)

@JacksonDataClass
sealed class RemindersAddResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property reminder the added reminder
 */
data class SuccessfulRemindersAddResponse(
    override val ok: Boolean,
    @JsonProperty("reminder") val reminder: Reminder
) : RemindersAddResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
data class ErrorRemindersAddResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : RemindersAddResponse(ok) {
    companion object
}

/**
 * Adds a Reminder
 *
 * @property text the reminder-text
 * @property triggerTimestamp the timestamp when the reminder should trigger
 * @property userId the user-id of the user that should be reminded
 */
data class RemindersAddRequest(
    @JsonProperty("text") val text: String,
    @InstantToInt @JsonProperty("time") val triggerTimestamp: Instant, //time when the reminder should trigger
    @JsonProperty("user") val userId: String? = null
) {
    companion object
}
