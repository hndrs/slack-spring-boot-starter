package com.kreait.slack.api.group.reminders

import com.kreait.slack.api.contract.jackson.group.reminders.ErrorRemindersListResponse
import com.kreait.slack.api.contract.jackson.group.reminders.SuccessfulRemindersListResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/reminders.list
 */
abstract class RemindersListMethod :
    ApiCallMethod<RemindersListMethod, SuccessfulRemindersListResponse, ErrorRemindersListResponse, Unit>()
