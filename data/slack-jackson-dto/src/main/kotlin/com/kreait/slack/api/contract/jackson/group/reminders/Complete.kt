package com.kreait.slack.api.contract.jackson.group.reminders

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "ok",
    visible = true
)
@JsonSubTypes(
    JsonSubTypes.Type(value = SuccessfulRemindersCompleteResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorRemindersCompleteResponse::class, name = "false")
)

@JacksonDataClass
sealed class RemindersCompleteResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 */
data class SuccessfulRemindersCompleteResponse(
    override val ok: Boolean
) : RemindersCompleteResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
data class ErrorRemindersCompleteResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : RemindersCompleteResponse(ok) {
    companion object
}

/**
 * completes a reminder
 *
 * @property reminderId the reminder of the completed reminder
 */
data class RemindersCompleteRequest(@JsonProperty("reminder") val reminderId: String) {
    companion object
}
