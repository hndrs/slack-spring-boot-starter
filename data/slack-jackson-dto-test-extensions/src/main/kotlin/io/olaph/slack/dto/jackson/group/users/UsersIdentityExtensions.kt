package io.olaph.slack.dto.jackson.group.users

import io.olaph.slack.dto.jackson.common.types.Identity
import io.olaph.slack.dto.jackson.common.types.sample

fun SuccessfulUsersIdentityResponse.Companion.sample(): SuccessfulUsersIdentityResponse {
    return SuccessfulUsersIdentityResponse(true, Identity(Identity.User.sample(), Identity.Team.sample()))
}

fun ErrorUsersIdentityResponse.Companion.sample(): ErrorUsersIdentityResponse {
    return ErrorUsersIdentityResponse(false, "")
}
