package com.kreait.slack.api.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorConversationsResponse
import com.kreait.slack.api.contract.jackson.group.users.ConversationsRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulConversationsResponse
import com.kreait.slack.api.group.ApiCallMethod

@Suppress("UNCHECKED_CAST")
abstract class UserConversationsMethod : ApiCallMethod<UserConversationsMethod, SuccessfulConversationsResponse, ErrorConversationsResponse, ConversationsRequest>()

