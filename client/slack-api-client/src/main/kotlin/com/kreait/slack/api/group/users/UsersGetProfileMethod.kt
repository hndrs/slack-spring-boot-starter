package com.kreait.slack.api.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorGetProfileResponse
import com.kreait.slack.api.contract.jackson.group.users.GetProfileRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulGetProfileResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/users.profile.get
 */
@Suppress("UNCHECKED_CAST")
abstract class UsersGetProfileMethod :
    ApiCallMethod<UsersGetProfileMethod, SuccessfulGetProfileResponse, ErrorGetProfileResponse, GetProfileRequest>()

