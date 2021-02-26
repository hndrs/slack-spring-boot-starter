package io.hndrs.slack.api.group.reminders

import io.hndrs.slack.api.contract.jackson.group.reminders.ErrorRemindersAddResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.RemindersAddRequest
import io.hndrs.slack.api.contract.jackson.group.reminders.SuccessfulRemindersAddResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/reminders.add
 */
abstract class RemindersAddMethod :
    io.hndrs.slack.api.group.ApiCallMethod<io.hndrs.slack.api.group.reminders.RemindersAddMethod, SuccessfulRemindersAddResponse, ErrorRemindersAddResponse, RemindersAddRequest>()
