package com.kreait.slack.api.contract.jackson.group.reminders

fun RemindersCompleteRequest.Companion.sample(): RemindersCompleteRequest {
    return RemindersCompleteRequest("")
}

fun SuccessfulRemindersCompleteResponse.Companion.sample(): SuccessfulRemindersCompleteResponse {
    return SuccessfulRemindersCompleteResponse(true)
}

fun ErrorRemindersCompleteResponse.Companion.sample(): ErrorRemindersCompleteResponse {
    return ErrorRemindersCompleteResponse(true, "")
}


