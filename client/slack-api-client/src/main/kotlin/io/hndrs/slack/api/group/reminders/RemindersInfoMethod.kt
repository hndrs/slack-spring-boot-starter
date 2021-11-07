package io.hndrs.slack.api.group.reminders

import io.hndrs.slack.api.contract.jackson.group.reminders.ErrorRemindersInfoResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.RemindersInfoRequest
import io.hndrs.slack.api.contract.jackson.group.reminders.SuccessfulRemindersInfoResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/reminders.info
 */
abstract class RemindersInfoMethod :
    ApiCallMethod<RemindersInfoMethod, SuccessfulRemindersInfoResponse,
            ErrorRemindersInfoResponse, RemindersInfoRequest>()
