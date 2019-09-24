package com.kreait.slack.api.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorListResponse
import com.kreait.slack.api.contract.jackson.group.users.ListRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulListResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/users.list
 */
@Suppress("UNCHECKED_CAST")
abstract class UserListMethod : ApiCallMethod<UserListMethod, SuccessfulListResponse, ErrorListResponse, ListRequest>()

