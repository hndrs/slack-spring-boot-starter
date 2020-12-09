package com.kreait.slack.api.group.reminders

import com.kreait.slack.api.contract.jackson.group.reminders.ErrorRemindersCompleteResponse
import com.kreait.slack.api.contract.jackson.group.reminders.RemindersCompleteRequest
import com.kreait.slack.api.contract.jackson.group.reminders.SuccessfulRemindersCompleteResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/reminders.complete
 */
abstract class RemindersCompleteMethod :
    ApiCallMethod<RemindersCompleteMethod, SuccessfulRemindersCompleteResponse,
            ErrorRemindersCompleteResponse, RemindersCompleteRequest>()
