package io.hndrs.slack.api.contract.jackson.group.oauth

import io.hndrs.slack.api.contract.jackson.common.types.Team
import io.hndrs.slack.api.contract.jackson.common.types.sample


fun AccessRequest.Companion.sample(): AccessRequest {
    return AccessRequest("", "", "")
}


fun SuccessfullAccessResponse.Companion.sample(): SuccessfullAccessResponse {
    return SuccessfullAccessResponse(
        true, "token", "scope", "users", "botUserId", "appId",
        Team.sample(), null
    )
}

fun ErrorAccessResponse.Companion.sample(): ErrorAccessResponse {
    return ErrorAccessResponse(false, "")
}
