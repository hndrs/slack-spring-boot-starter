package com.kreait.slack.api.group.reminders

import com.kreait.slack.api.contract.jackson.group.reminders.ErrorRemindersDeleteResponse
import com.kreait.slack.api.contract.jackson.group.reminders.RemindersDeleteRequest
import com.kreait.slack.api.contract.jackson.group.reminders.SuccessfulRemindersDeleteResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/reminders.delete
 */
abstract class RemindersDeleteMethod : ApiCallMethod<RemindersDeleteMethod, SuccessfulRemindersDeleteResponse, ErrorRemindersDeleteResponse, RemindersDeleteRequest>()
