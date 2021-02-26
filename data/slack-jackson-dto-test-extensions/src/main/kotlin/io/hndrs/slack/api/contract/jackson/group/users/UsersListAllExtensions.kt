package io.hndrs.slack.api.contract.jackson.group.users

fun SuccessfulListAllResponse.Companion.sample(): SuccessfulListAllResponse {
    return SuccessfulListAllResponse(listOf())
}

fun ErrorListAllResponse.Companion.sample(): ErrorListAllResponse {
    return ErrorListAllResponse("")
}

fun ListAllRequest.Companion.sample(): ListAllRequest {
    return ListAllRequest(false)
}
