package com.kreait.slack.api.group.users

import com.kreait.slack.api.group.ApiCallMethod
import com.kreait.slack.api.contract.jackson.group.users.ErrorUserListResponse
import com.kreait.slack.api.contract.jackson.group.users.SlackUserListRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUserListResponse

@Suppress("UNCHECKED_CAST")
abstract class UserListMethod : ApiCallMethod<UserListMethod, SuccessfulUserListResponse, ErrorUserListResponse, SlackUserListRequest>()

