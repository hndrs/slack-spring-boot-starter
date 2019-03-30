package io.olaph.slack.dto.jackson.group.dialog

fun SlackOpenDialogRequest.Companion.sample(): SlackOpenDialogRequest {
    return SlackOpenDialogRequest(Dialog.sample(), "")
}

fun Dialog.Companion.sample(): Dialog {
    return Dialog("", "", null, listOf())
}

fun SuccessfulOpenDialogResponse.Companion.sample(): SuccessfulOpenDialogResponse {
    return SuccessfulOpenDialogResponse(true)
}

fun ErrorOpenDialogResponse.Companion.sample(): ErrorOpenDialogResponse = ErrorOpenDialogResponse(false, "error")
