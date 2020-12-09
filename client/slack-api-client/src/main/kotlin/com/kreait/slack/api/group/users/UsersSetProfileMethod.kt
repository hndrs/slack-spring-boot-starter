package com.kreait.slack.api.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorSetProfileResponse
import com.kreait.slack.api.contract.jackson.group.users.SetProfileRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulSetProfileResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 *
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/users.profile.set
 */
@Suppress("UNCHECKED_CAST")
abstract class UsersSetProfileMethod :
    ApiCallMethod<UsersSetProfileMethod, SuccessfulSetProfileResponse, ErrorSetProfileResponse, SetProfileRequest>()

