package io.hndrs.slack.api.group.users

import io.hndrs.slack.api.contract.jackson.group.users.ErrorIdentityResponse
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulIdentityResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/users.identity
 */
@Suppress("UNCHECKED_CAST")
abstract class UsersIdentityMethod :
    ApiCallMethod<UsersIdentityMethod, SuccessfulIdentityResponse, ErrorIdentityResponse, Unit>()

