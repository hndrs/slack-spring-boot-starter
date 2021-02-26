package io.hndrs.slack.api.group.users

import io.hndrs.slack.api.contract.jackson.group.users.ErrorListResponse
import io.hndrs.slack.api.contract.jackson.group.users.ListRequest
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulListResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/users.list
 */
@Suppress("UNCHECKED_CAST")
abstract class UserListMethod : io.hndrs.slack.api.group.ApiCallMethod<io.hndrs.slack.api.group.users.UserListMethod, SuccessfulListResponse, ErrorListResponse, ListRequest>()

