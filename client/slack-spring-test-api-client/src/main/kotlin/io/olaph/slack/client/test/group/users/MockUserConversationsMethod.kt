package io.olaph.slack.client.test.group.users

import io.olaph.slack.client.group.users.UserConversationsMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.conversations.ErrorGetConversationListResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccesfulGetConversationListResponse
import io.olaph.slack.dto.jackson.group.users.UserConversationsResponse

class MockUserConversationsMethod : UserConversationsMethod(), MockMethod<SuccesfulGetConversationListResponse, ErrorGetConversationListResponse, UserConversationsResponse>