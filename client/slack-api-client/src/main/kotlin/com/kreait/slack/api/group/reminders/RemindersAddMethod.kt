package com.kreait.slack.api.group.reminders

import com.kreait.slack.api.contract.jackson.group.reminders.ErrorRemindersAddResponse
import com.kreait.slack.api.contract.jackson.group.reminders.RemindersAddRequest
import com.kreait.slack.api.contract.jackson.group.reminders.SuccessfulRemindersAddResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class RemindersAddMethod : ApiCallMethod<RemindersAddMethod, SuccessfulRemindersAddResponse, ErrorRemindersAddResponse, RemindersAddRequest>()