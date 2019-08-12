package com.kreait.slack.api.contract.jackson.group.users

import com.kreait.slack.api.contract.jackson.common.types.Identity
import com.kreait.slack.api.contract.jackson.common.types.sample

fun SuccessfulUsersIdentityResponse.Companion.sample(): SuccessfulUsersIdentityResponse {
    return SuccessfulUsersIdentityResponse(true, Identity(Identity.User.sample(), Identity.Team.sample()))
}

fun ErrorUsersIdentityResponse.Companion.sample(): ErrorUsersIdentityResponse {
    return ErrorUsersIdentityResponse(false, "")
}
