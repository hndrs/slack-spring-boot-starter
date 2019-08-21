package com.kreait.slack.api.contract.jackson.group.reminders

fun RemindersDeleteRequest.Companion.sample(): RemindersDeleteRequest {
    return RemindersDeleteRequest("")
}

fun SuccessfulRemindersDeleteResponse.Companion.sample(): SuccessfulRemindersDeleteResponse {
    return SuccessfulRemindersDeleteResponse(true)
}

fun ErrorRemindersDeleteResponse.Companion.sample(): ErrorRemindersDeleteResponse {
    return ErrorRemindersDeleteResponse(true, "")
}


