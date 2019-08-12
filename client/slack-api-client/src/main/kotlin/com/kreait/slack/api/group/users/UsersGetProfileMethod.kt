package com.kreait.slack.api.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorGetProfileResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulGetProfileResponse
import com.kreait.slack.api.contract.jackson.group.users.GetProfileRequest
import com.kreait.slack.api.group.ApiCallMethod

@Suppress("UNCHECKED_CAST")
abstract class UsersGetProfileMethod : ApiCallMethod<UsersGetProfileMethod, SuccessfulGetProfileResponse, ErrorGetProfileResponse, GetProfileRequest>()

