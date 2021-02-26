package io.hndrs.slack.api.contract.jackson.group.reminders

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.hndrs.slack.api.contract.jackson.util.JacksonDataClass

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "ok",
    visible = true
)
@JsonSubTypes(
    JsonSubTypes.Type(value = SuccessfulRemindersDeleteResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorRemindersDeleteResponse::class, name = "false")
)

@JacksonDataClass
sealed class RemindersDeleteResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 */
data class SuccessfulRemindersDeleteResponse(
    override val ok: Boolean
) : RemindersDeleteResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
data class ErrorRemindersDeleteResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : RemindersDeleteResponse(ok) {
    companion object
}

/**
 * Removes a Reminder
 *
 * @property reminderId the reminder-Id of the reminder that should be removed
 */
data class RemindersDeleteRequest(@JsonProperty("reminder") val reminderId: String) {
    companion object
}
