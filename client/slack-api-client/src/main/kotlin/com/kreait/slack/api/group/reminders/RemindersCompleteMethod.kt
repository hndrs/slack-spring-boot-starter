package com.kreait.slack.api.group.reminders

import com.kreait.slack.api.contract.jackson.group.reminders.ErrorRemindersCompleteResponse
import com.kreait.slack.api.contract.jackson.group.reminders.RemindersCompleteRequest
import com.kreait.slack.api.contract.jackson.group.reminders.SuccessfulRemindersCompleteResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class RemindersCompleteMethod : ApiCallMethod<RemindersCompleteMethod, SuccessfulRemindersCompleteResponse, ErrorRemindersCompleteResponse, RemindersCompleteRequest>()