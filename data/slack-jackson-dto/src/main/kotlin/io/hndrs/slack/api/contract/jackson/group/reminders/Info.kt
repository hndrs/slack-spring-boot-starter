package io.hndrs.slack.api.contract.jackson.group.reminders

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.hndrs.slack.api.contract.jackson.group.reminders.common.Reminder
import io.hndrs.slack.api.contract.jackson.util.JacksonDataClass

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "ok",
    visible = true
)
@JsonSubTypes(
    JsonSubTypes.Type(value = SuccessfulRemindersInfoResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorRemindersInfoResponse::class, name = "false")
)

@JacksonDataClass
sealed class RemindersInfoResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property error
 */
data class SuccessfulRemindersInfoResponse(
    override val ok: Boolean,
    @JsonProperty("reminder") val reminder: Reminder
) : RemindersInfoResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
data class ErrorRemindersInfoResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : RemindersInfoResponse(ok) {
    companion object
}

/**
 * Gets information about a reminder
 *
 * @property reminderId the id of the reminder you want to request detailled information for
 */
data class RemindersInfoRequest(@JsonProperty("reminder") val reminderId: String) {
    companion object
}
