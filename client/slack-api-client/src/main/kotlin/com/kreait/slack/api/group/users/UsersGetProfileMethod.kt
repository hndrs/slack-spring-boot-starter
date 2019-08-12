package com.kreait.slack.api.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersGetProfileResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersGetProfileResponse
import com.kreait.slack.api.contract.jackson.group.users.UsersGetProfileRequest
import com.kreait.slack.api.group.ApiCallMethod

@Suppress("UNCHECKED_CAST")
abstract class UsersGetProfileMethod : ApiCallMethod<UsersGetProfileMethod, SuccessfulUsersGetProfileResponse, ErrorUsersGetProfileResponse, UsersGetProfileRequest>()

