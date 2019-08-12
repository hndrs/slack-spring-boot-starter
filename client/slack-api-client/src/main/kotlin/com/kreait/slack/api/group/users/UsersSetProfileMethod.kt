package com.kreait.slack.api.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersSetProfileResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersSetProfileResponse
import com.kreait.slack.api.group.ApiCallMethod

@Suppress("UNCHECKED_CAST")
abstract class UsersSetProfileMethod : ApiCallMethod<UsersSetProfileMethod, SuccessfulUsersSetProfileResponse, ErrorUsersSetProfileResponse, Unit>()

