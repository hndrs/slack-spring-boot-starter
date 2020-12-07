package com.kreait.slack.api.contract.jackson.group.users

import com.kreait.slack.api.contract.jackson.common.types.Identity
import com.kreait.slack.api.contract.jackson.common.types.Team
import com.kreait.slack.api.contract.jackson.common.types.sample

fun SuccessfulIdentityResponse.Companion.sample(): SuccessfulIdentityResponse {
    return SuccessfulIdentityResponse(true, Identity(Identity.User.sample(), Team.sample()))
}

fun ErrorIdentityResponse.Companion.sample(): ErrorIdentityResponse {
    return ErrorIdentityResponse(false, "")
}
