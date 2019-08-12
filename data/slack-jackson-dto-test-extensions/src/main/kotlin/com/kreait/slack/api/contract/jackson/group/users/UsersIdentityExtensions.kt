package com.kreait.slack.api.contract.jackson.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersIdentityResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersIdentityResponse
import com.kreait.slack.api.contract.jackson.common.types.Identity

fun SuccessfulUsersIdentityResponse.Companion.sample(): SuccessfulUsersIdentityResponse {
    return SuccessfulUsersIdentityResponse(true, Identity(Identity.User.sample(), Identity.Team.sample()))
}

fun ErrorUsersIdentityResponse.Companion.sample(): ErrorUsersIdentityResponse {
    return ErrorUsersIdentityResponse(false, "")
}
