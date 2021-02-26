package io.hndrs.slack.api.group.reminders

import io.hndrs.slack.api.contract.jackson.group.reminders.ErrorRemindersCompleteResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.RemindersCompleteRequest
import io.hndrs.slack.api.contract.jackson.group.reminders.SuccessfulRemindersCompleteResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/reminders.complete
 */
abstract class RemindersCompleteMethod :
    io.hndrs.slack.api.group.ApiCallMethod<io.hndrs.slack.api.group.reminders.RemindersCompleteMethod, SuccessfulRemindersCompleteResponse,
            ErrorRemindersCompleteResponse, RemindersCompleteRequest>()
