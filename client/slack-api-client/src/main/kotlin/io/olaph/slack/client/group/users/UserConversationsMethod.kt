package io.olaph.slack.client.group.users

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.users.ErrorUserConversationsResponse
import io.olaph.slack.dto.jackson.group.users.SlackUserConversationListRequest
import io.olaph.slack.dto.jackson.group.users.SuccessfulUserConversationsResponse

@Suppress("UNCHECKED_CAST")
abstract class UserConversationsMethod : ApiCallMethod<UserConversationsMethod, SuccessfulUserConversationsResponse, ErrorUserConversationsResponse, SlackUserConversationListRequest>()

