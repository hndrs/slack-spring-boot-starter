package io.olaph.slack.client.group.users

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.users.ErrorUsersIdentityResponse
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersIdentityResponse

@Suppress("UNCHECKED_CAST")
abstract class UsersIdentityMethod : ApiCallMethod<UsersIdentityMethod, SuccessfulUsersIdentityResponse, ErrorUsersIdentityResponse, Unit>()

