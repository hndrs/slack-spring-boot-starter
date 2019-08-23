package com.kreait.slack.api.test.group.chat

import com.kreait.slack.api.contract.jackson.group.chat.ChatDeleteRequest
import com.kreait.slack.api.contract.jackson.group.chat.ChatGetPermalinkRequest
import com.kreait.slack.api.contract.jackson.group.chat.ChatMeMessageRequest
import com.kreait.slack.api.contract.jackson.group.chat.ChatUnfurlRequest
import com.kreait.slack.api.contract.jackson.group.chat.ErrorChatDeleteResponse
import com.kreait.slack.api.contract.jackson.group.chat.ErrorChatGetPermalinkResponse
import com.kreait.slack.api.contract.jackson.group.chat.ErrorChatMeMessageResponse
import com.kreait.slack.api.contract.jackson.group.chat.ErrorChatUnfurlResponse
import com.kreait.slack.api.contract.jackson.group.chat.ErrorPostEphemeralResponse
import com.kreait.slack.api.contract.jackson.group.chat.ErrorPostMessageResponse
import com.kreait.slack.api.contract.jackson.group.chat.PostEphemeralRequest
import com.kreait.slack.api.contract.jackson.group.chat.PostMessageRequest
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulChatDeleteResponse
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulChatGetPermalinkResponse
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulChatMeMessageResponse
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulChatUnfurlResponse
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulChatUpdateResponse
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulPostMessageResponse
import com.kreait.slack.api.contract.jackson.group.chat.sample
import com.kreait.slack.api.test.DynamicMockGroupTests
import com.kreait.slack.api.test.MockMetaInfo
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class DynamicMockChatTests {

    @TestFactory
    fun methodInvocations(): List<DynamicTest> = DynamicMockGroupTests.methodInvocations(testCases = testCases())

    private fun testCases() = listOf(
            MockMetaInfo(MockChatDelete(), mock { }, SuccessfulChatDeleteResponse.sample(), mock { }, ErrorChatDeleteResponse.sample(), ChatDeleteRequest.sample()),
            MockMetaInfo(MockChatGetPermalink(), mock { }, SuccessfulChatGetPermalinkResponse.sample(), mock { }, ErrorChatGetPermalinkResponse.sample(), ChatGetPermalinkRequest.sample()),
            MockMetaInfo(MockChatMeMessage(), mock { }, SuccessfulChatMeMessageResponse.sample(), mock { }, ErrorChatMeMessageResponse.sample(), ChatMeMessageRequest.sample()),
            MockMetaInfo(MockChatPostEphemeral(), mock { }, SuccessfulChatUpdateResponse.sample(), mock { }, ErrorPostEphemeralResponse.sample(), PostEphemeralRequest.sample()),
            MockMetaInfo(MockChatPostMessage(), mock { }, SuccessfulPostMessageResponse.sample(), mock { }, ErrorPostMessageResponse.sample(), PostMessageRequest.sample()),
            MockMetaInfo(MockChatUnfurl(), mock { }, SuccessfulChatUnfurlResponse.sample(), mock { }, ErrorChatUnfurlResponse.sample(), ChatUnfurlRequest.sample())
    )
}