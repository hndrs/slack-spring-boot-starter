package io.hndrs.slack.api.contract.jackson.group.dialog

fun OpenDialogRequest.Companion.sample(): OpenDialogRequest {
    return OpenDialogRequest(Dialog.sample(), "")
}

fun Dialog.Companion.sample(): Dialog {
    return Dialog("", "", null, listOf())
}

fun SuccessfulOpenDialogResponse.Companion.sample(): SuccessfulOpenDialogResponse {
    return SuccessfulOpenDialogResponse(true)
}

fun ErrorOpenDialogResponse.Companion.sample(): ErrorOpenDialogResponse = ErrorOpenDialogResponse(false, "error")
