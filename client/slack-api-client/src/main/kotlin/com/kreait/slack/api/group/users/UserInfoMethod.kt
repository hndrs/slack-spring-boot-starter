package com.kreait.slack.api.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersInfoResponse
import com.kreait.slack.api.contract.jackson.group.users.SlackUserInfoRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersInfoResponse
import com.kreait.slack.api.group.ApiCallMethod

@Suppress("UNCHECKED_CAST")
abstract class UsersInfoMethod : ApiCallMethod<UsersInfoMethod, SuccessfulUsersInfoResponse, ErrorUsersInfoResponse, SlackUserInfoRequest>()

