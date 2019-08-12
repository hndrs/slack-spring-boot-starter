package com.kreait.slack.api.group.users

import com.kreait.slack.api.group.ApiCallMethod
import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersSetPresenceResponse
import com.kreait.slack.api.contract.jackson.group.users.SlackUsersSetPresenceRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersSetPresenceResponse

@Suppress("UNCHECKED_CAST")
abstract class UsersSetPresenceMethod : ApiCallMethod<UsersSetPresenceMethod, SuccessfulUsersSetPresenceResponse, ErrorUsersSetPresenceResponse, SlackUsersSetPresenceRequest>()
