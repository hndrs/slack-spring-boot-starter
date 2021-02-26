package io.hndrs.slack.api.test.group.chat

import io.hndrs.slack.api.contract.jackson.group.chat.ChatDeleteRequest
import io.hndrs.slack.api.contract.jackson.group.chat.ChatGetPermalinkRequest
import io.hndrs.slack.api.contract.jackson.group.chat.ChatMeMessageRequest
import io.hndrs.slack.api.contract.jackson.group.chat.ChatUnfurlRequest
import io.hndrs.slack.api.contract.jackson.group.chat.ChatUpdateRequest
import io.hndrs.slack.api.contract.jackson.group.chat.ErrorChatDeleteResponse
import io.hndrs.slack.api.contract.jackson.group.chat.ErrorChatGetPermalinkResponse
import io.hndrs.slack.api.contract.jackson.group.chat.ErrorChatMeMessageResponse
import io.hndrs.slack.api.contract.jackson.group.chat.ErrorChatUnfurlResponse
import io.hndrs.slack.api.contract.jackson.group.chat.ErrorChatUpdateResponse
import io.hndrs.slack.api.contract.jackson.group.chat.ErrorPostEphemeralResponse
import io.hndrs.slack.api.contract.jackson.group.chat.ErrorPostMessageResponse
import io.hndrs.slack.api.contract.jackson.group.chat.PostEphemeralRequest
import io.hndrs.slack.api.contract.jackson.group.chat.PostMessageRequest
import io.hndrs.slack.api.contract.jackson.group.chat.SuccessfulChatDeleteResponse
import io.hndrs.slack.api.contract.jackson.group.chat.SuccessfulChatGetPermalinkResponse
import io.hndrs.slack.api.contract.jackson.group.chat.SuccessfulChatMeMessageResponse
import io.hndrs.slack.api.contract.jackson.group.chat.SuccessfulChatUnfurlResponse
import io.hndrs.slack.api.contract.jackson.group.chat.SuccessfulChatUpdateResponse
import io.hndrs.slack.api.contract.jackson.group.chat.SuccessfulPostEphemeralResponse
import io.hndrs.slack.api.contract.jackson.group.chat.SuccessfulPostMessageResponse
import io.hndrs.slack.api.contract.jackson.group.chat.sample
import io.hndrs.slack.api.test.DynamicMockGroupTests
import io.hndrs.slack.api.test.MockMetaInfo
import io.hndrs.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class DynamicMockChatTests {

    @TestFactory
    fun methodInvocations(): List<DynamicTest> = DynamicMockGroupTests.methodInvocations(testCases = testCases())

    private val client = MockSlackClient()

    private fun testCases() = listOf(
            MockMetaInfo({ client.chat().delete("") }, mock { }, SuccessfulChatDeleteResponse.sample(), mock { }, ErrorChatDeleteResponse.sample(), ChatDeleteRequest.sample()),
            MockMetaInfo({ client.chat().getPermalink("") }, mock { }, SuccessfulChatGetPermalinkResponse.sample(), mock { }, ErrorChatGetPermalinkResponse.sample(), ChatGetPermalinkRequest.sample()),
            MockMetaInfo({ client.chat().meMessage("") }, mock { }, SuccessfulChatMeMessageResponse.sample(), mock { }, ErrorChatMeMessageResponse.sample(), ChatMeMessageRequest.sample()),
            MockMetaInfo({ client.chat().postEphemeral("") }, mock { }, SuccessfulPostEphemeralResponse.sample(), mock { }, ErrorPostEphemeralResponse.sample(), PostEphemeralRequest.sample()),
            MockMetaInfo({ client.chat().postMessage("") }, mock { }, SuccessfulPostMessageResponse.sample(), mock { }, ErrorPostMessageResponse.sample(), PostMessageRequest.sample()),
            MockMetaInfo({ client.chat().unfurl("") }, mock { }, SuccessfulChatUnfurlResponse.sample(), mock { }, ErrorChatUnfurlResponse.sample(), ChatUnfurlRequest.sample()),
            MockMetaInfo({ client.chat().update("") }, mock { }, SuccessfulChatUpdateResponse.sample(), mock { }, ErrorChatUpdateResponse.sample(), ChatUpdateRequest.sample())
    )
}
