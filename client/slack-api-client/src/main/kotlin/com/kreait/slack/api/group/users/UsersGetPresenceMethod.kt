package com.kreait.slack.api.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersGetPresenceResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersGetPresenceResponse
import com.kreait.slack.api.contract.jackson.group.users.UsersGetPresenceRequest
import com.kreait.slack.api.group.ApiCallMethod

@Suppress("UNCHECKED_CAST")
abstract class UsersGetPresenceMethod : ApiCallMethod<UsersGetPresenceMethod, SuccessfulUsersGetPresenceResponse, ErrorUsersGetPresenceResponse, UsersGetPresenceRequest>()
