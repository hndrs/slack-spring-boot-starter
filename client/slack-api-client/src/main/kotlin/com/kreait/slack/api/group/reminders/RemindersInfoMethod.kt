package com.kreait.slack.api.group.reminders

import com.kreait.slack.api.contract.jackson.group.reminders.ErrorRemindersInfoResponse
import com.kreait.slack.api.contract.jackson.group.reminders.RemindersInfoRequest
import com.kreait.slack.api.contract.jackson.group.reminders.SuccessfulRemindersInfoResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/reminders.info
 */
abstract class RemindersInfoMethod :
    ApiCallMethod<RemindersInfoMethod, SuccessfulRemindersInfoResponse,
            ErrorRemindersInfoResponse, RemindersInfoRequest>()
