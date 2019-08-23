package com.kreait.slack.api.contract.jackson.group.reminders

import com.kreait.slack.api.contract.jackson.group.reminders.common.Reminder

fun SuccessfulRemindersListResponse.Companion.sample(): SuccessfulRemindersListResponse {
    return SuccessfulRemindersListResponse(true, listOf(Reminder.sample()))
}


fun ErrorRemindersListResponse.Companion.sample(): ErrorRemindersListResponse {
    return ErrorRemindersListResponse(true, "")
}


