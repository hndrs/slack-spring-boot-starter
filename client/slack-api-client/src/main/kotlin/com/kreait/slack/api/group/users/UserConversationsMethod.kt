package com.kreait.slack.api.group.users

import com.kreait.slack.api.group.ApiCallMethod
import com.kreait.slack.api.contract.jackson.group.users.ErrorUserConversationsResponse
import com.kreait.slack.api.contract.jackson.group.users.SlackUserConversationListRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUserConversationsResponse

@Suppress("UNCHECKED_CAST")
abstract class UserConversationsMethod : ApiCallMethod<UserConversationsMethod, SuccessfulUserConversationsResponse, ErrorUserConversationsResponse, SlackUserConversationListRequest>()

