package io.olaph.slack.dto.jackson.group.oauth


fun OauthAccessRequest.Companion.sample(): OauthAccessRequest {
    return OauthAccessRequest("", "", "")
}

fun Bot.Companion.sample(): Bot {
    return Bot("", "")
}

fun IncomingWebhook.Companion.sample(): IncomingWebhook {
    return IncomingWebhook("", "", "", "")
}

fun SuccessFullOauthAccessResponse.Companion.sample(): SuccessFullOauthAccessResponse {
    return SuccessFullOauthAccessResponse(true, "token", "scope", "UserId", "TeamName", "TeamId",
            IncomingWebhook.sample(),
            Bot.sample())
}

fun ErrorOauthAccessResponse.Companion.sample(): ErrorOauthAccessResponse {
    return ErrorOauthAccessResponse(false, "")
}
