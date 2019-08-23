package com.kreait.slack.api.group.reminders

import com.kreait.slack.api.contract.jackson.group.reminders.ErrorRemindersInfoResponse
import com.kreait.slack.api.contract.jackson.group.reminders.RemindersInfoRequest
import com.kreait.slack.api.contract.jackson.group.reminders.SuccessfulRemindersInfoResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class RemindersInfoMethod : ApiCallMethod<RemindersInfoMethod, SuccessfulRemindersInfoResponse, ErrorRemindersInfoResponse, RemindersInfoRequest>()