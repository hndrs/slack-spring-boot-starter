package com.kreait.slack.api.contract.jackson.group.reminders

fun SuccessfulRemindersListResponse.Companion.sample(): SuccessfulRemindersListResponse {
    return SuccessfulRemindersListResponse(true, listOf(Reminder.sample()))
}

fun Reminder.Companion.sample(): Reminder {
    return Reminder(0, "", "", false, "", 0, "")
}

fun ErrorRemindersListResponse.Companion.sample(): ErrorRemindersListResponse {
    return ErrorRemindersListResponse(true, "")
}


