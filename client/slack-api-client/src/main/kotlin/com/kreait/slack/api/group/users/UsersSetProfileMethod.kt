package com.kreait.slack.api.group.users

import com.kreait.slack.api.group.ApiCallMethod
import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersSetProfileResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersSetProfileResponse

@Suppress("UNCHECKED_CAST")
abstract class UsersSetProfileMethod : ApiCallMethod<UsersSetProfileMethod, SuccessfulUsersSetProfileResponse, ErrorUsersSetProfileResponse, Unit>()

