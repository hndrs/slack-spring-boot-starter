package io.hndrs.slack.api.spring.group.chat

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
import io.hndrs.slack.api.spring.DynamicGroupTests
import io.hndrs.slack.api.spring.MetaInfo
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.springframework.web.client.RestTemplate


@DisplayName("Chat MethodGroup")
class MethodInvocationTest {

    protected lateinit var mockTemplate: RestTemplate


    @BeforeEach
    fun setup() {
        mockTemplate = io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate()
    }

    @TestFactory
    @DisplayName("Method Invocation Tests")
    fun methodInvocations(): List<DynamicTest> {
        return DynamicGroupTests.methodInvocations(testCases(), mockTemplate)
    }

    private fun testCases() = listOf(
            MetaInfo("chat.delete", SuccessfulChatDeleteResponse.sample(), ErrorChatDeleteResponse.sample(), ChatDeleteRequest.sample(), SpringDeleteMethod("", mockTemplate)),
            MetaInfo("chat.getPermalink", SuccessfulChatGetPermalinkResponse.sample(), ErrorChatGetPermalinkResponse.sample(), ChatGetPermalinkRequest.sample(), SpringGetPermalinkMethod("", mockTemplate)),
            MetaInfo("chat.meMessage", SuccessfulChatMeMessageResponse.sample(), ErrorChatMeMessageResponse.sample(), ChatMeMessageRequest.sample(), SpringMeMessageMethod("", mockTemplate)),
            MetaInfo("chat.postEphemeral", SuccessfulPostEphemeralResponse.sample(), ErrorPostEphemeralResponse.sample(), PostEphemeralRequest.sample(), SpringPostEphemeralMethod("", mockTemplate)),
            MetaInfo("chat.postMessage", SuccessfulPostMessageResponse.sample(), ErrorPostMessageResponse.sample(), PostMessageRequest.sample(), SpringPostMessageMethod("", mockTemplate)),
            MetaInfo("chat.unfurl", SuccessfulChatUnfurlResponse.sample(), ErrorChatUnfurlResponse.sample(), ChatUnfurlRequest.sample(), SpringUnfurlMethod("", mockTemplate)),
            MetaInfo("chat.update", SuccessfulChatUpdateResponse.sample(), ErrorChatUpdateResponse.sample(), ChatUpdateRequest.sample(), SpringUpdateMethod("", mockTemplate))
    )


}
