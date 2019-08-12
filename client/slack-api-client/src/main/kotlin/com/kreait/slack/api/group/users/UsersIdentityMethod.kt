package com.kreait.slack.api.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersIdentityResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersIdentityResponse
import com.kreait.slack.api.group.ApiCallMethod

@Suppress("UNCHECKED_CAST")
abstract class UsersIdentityMethod : ApiCallMethod<UsersIdentityMethod, SuccessfulUsersIdentityResponse, ErrorUsersIdentityResponse, Unit>()

