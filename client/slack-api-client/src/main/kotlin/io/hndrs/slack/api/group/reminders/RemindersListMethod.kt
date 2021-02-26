package io.hndrs.slack.api.group.reminders

import io.hndrs.slack.api.contract.jackson.group.reminders.ErrorRemindersListResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.SuccessfulRemindersListResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/reminders.list
 */
abstract class RemindersListMethod :
    io.hndrs.slack.api.group.ApiCallMethod<io.hndrs.slack.api.group.reminders.RemindersListMethod, SuccessfulRemindersListResponse, ErrorRemindersListResponse, Unit>()
