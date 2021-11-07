package io.hndrs.slack.api.group.users

import io.hndrs.slack.api.contract.jackson.group.users.ErrorListAllResponse
import io.hndrs.slack.api.contract.jackson.group.users.ListAllRequest
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulListAllResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/users.list
 */
@Suppress("UNCHECKED_CAST")
abstract class UserListAllMethod :
    ApiCallMethod<UserListAllMethod, SuccessfulListAllResponse, ErrorListAllResponse, ListAllRequest>()
