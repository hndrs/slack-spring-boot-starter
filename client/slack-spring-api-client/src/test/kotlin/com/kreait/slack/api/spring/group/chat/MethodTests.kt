package com.kreait.slack.api.spring.group.chat

import com.kreait.slack.api.contract.jackson.group.chat.ChatDeleteRequest
import com.kreait.slack.api.contract.jackson.group.chat.ChatGetPermalinkRequest
import com.kreait.slack.api.contract.jackson.group.chat.ChatMeMessageRequest
import com.kreait.slack.api.contract.jackson.group.chat.ChatUnfurlRequest
import com.kreait.slack.api.contract.jackson.group.chat.ChatUpdateRequest
import com.kreait.slack.api.contract.jackson.group.chat.ErrorChatDeleteResponse
import com.kreait.slack.api.contract.jackson.group.chat.ErrorChatGetPermalinkResponse
import com.kreait.slack.api.contract.jackson.group.chat.ErrorChatMeMessageResponse
import com.kreait.slack.api.contract.jackson.group.chat.ErrorChatUnfurlResponse
import com.kreait.slack.api.contract.jackson.group.chat.ErrorChatUpdateResponse
import com.kreait.slack.api.contract.jackson.group.chat.ErrorPostEphemeralResponse
import com.kreait.slack.api.contract.jackson.group.chat.ErrorPostMessageResponse
import com.kreait.slack.api.contract.jackson.group.chat.PostEphemeralRequest
import com.kreait.slack.api.contract.jackson.group.chat.PostMessageRequest
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulChatDeleteResponse
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulChatGetPermalinkResponse
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulChatMeMessageResponse
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulChatUnfurlResponse
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulChatUpdateResponse
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulPostEphemeralResponse
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulPostMessageResponse
import com.kreait.slack.api.contract.jackson.group.chat.sample
import com.kreait.slack.api.spring.DynamicGroupTests
import com.kreait.slack.api.spring.MetaInfo
import com.kreait.slack.api.spring.group.RestTemplateFactory
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
        mockTemplate = RestTemplateFactory.slackTemplate()
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
