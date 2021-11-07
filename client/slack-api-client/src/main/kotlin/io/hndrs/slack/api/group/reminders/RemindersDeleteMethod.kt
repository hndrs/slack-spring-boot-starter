package io.hndrs.slack.api.group.reminders

import io.hndrs.slack.api.contract.jackson.group.reminders.ErrorRemindersDeleteResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.RemindersDeleteRequest
import io.hndrs.slack.api.contract.jackson.group.reminders.SuccessfulRemindersDeleteResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/reminders.delete
 */
abstract class RemindersDeleteMethod :
    ApiCallMethod<RemindersDeleteMethod, SuccessfulRemindersDeleteResponse,
            ErrorRemindersDeleteResponse, RemindersDeleteRequest>()
