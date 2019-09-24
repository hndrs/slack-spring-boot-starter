package com.kreait.slack.api.group.reminders

import com.kreait.slack.api.contract.jackson.group.reminders.ErrorRemindersAddResponse
import com.kreait.slack.api.contract.jackson.group.reminders.RemindersAddRequest
import com.kreait.slack.api.contract.jackson.group.reminders.SuccessfulRemindersAddResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/reminders.add
 */
abstract class RemindersAddMethod : ApiCallMethod<RemindersAddMethod, SuccessfulRemindersAddResponse, ErrorRemindersAddResponse, RemindersAddRequest>()
