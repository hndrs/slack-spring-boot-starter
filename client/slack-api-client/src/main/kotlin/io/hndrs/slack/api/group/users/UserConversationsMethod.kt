package io.hndrs.slack.api.group.users

import io.hndrs.slack.api.contract.jackson.group.users.ConversationsRequest
import io.hndrs.slack.api.contract.jackson.group.users.ErrorConversationsResponse
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulConversationsResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/users.conversations
 */
@Suppress("UNCHECKED_CAST")
abstract class UserConversationsMethod :
    ApiCallMethod<UserConversationsMethod, SuccessfulConversationsResponse,
            ErrorConversationsResponse, ConversationsRequest>()

