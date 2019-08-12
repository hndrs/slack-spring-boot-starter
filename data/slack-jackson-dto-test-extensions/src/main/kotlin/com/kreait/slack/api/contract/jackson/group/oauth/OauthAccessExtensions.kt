package com.kreait.slack.api.contract.jackson.group.oauth


fun AccessRequest.Companion.sample(): AccessRequest {
    return AccessRequest("", "", "")
}

fun Bot.Companion.sample(): Bot {
    return Bot("", "")
}

fun IncomingWebhook.Companion.sample(): IncomingWebhook {
    return IncomingWebhook("", "", "", "")
}

fun SuccessfullAccessResponse.Companion.sample(): SuccessfullAccessResponse {
    return SuccessfullAccessResponse(true, "token", "scope", "UserId", "TeamName", "TeamId",
            IncomingWebhook.sample(),
            Bot.sample())
}

fun ErrorAccessResponse.Companion.sample(): ErrorAccessResponse {
    return ErrorAccessResponse(false, "")
}
