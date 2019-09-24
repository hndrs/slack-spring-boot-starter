package com.kreait.slack.api.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorIdentityResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulIdentityResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/users.identity
 */
@Suppress("UNCHECKED_CAST")
abstract class UsersIdentityMethod : ApiCallMethod<UsersIdentityMethod, SuccessfulIdentityResponse, ErrorIdentityResponse, Unit>()

