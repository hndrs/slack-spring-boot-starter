package com.kreait.slack.api.spring.group.im

import com.kreait.slack.api.contract.jackson.group.im.ErrorImCloseResponse
import com.kreait.slack.api.contract.jackson.group.im.ErrorImHistoryResponse
import com.kreait.slack.api.contract.jackson.group.im.ErrorImListResponse
import com.kreait.slack.api.contract.jackson.group.im.ErrorImMarkResponse
import com.kreait.slack.api.contract.jackson.group.im.ErrorImOpenResponse
import com.kreait.slack.api.contract.jackson.group.im.ErrorImRepliesResponse
import com.kreait.slack.api.contract.jackson.group.im.ImCloseRequest
import com.kreait.slack.api.contract.jackson.group.im.ImHistoryRequest
import com.kreait.slack.api.contract.jackson.group.im.ImListRequest
import com.kreait.slack.api.contract.jackson.group.im.ImMarkRequest
import com.kreait.slack.api.contract.jackson.group.im.ImOpenRequest
import com.kreait.slack.api.contract.jackson.group.im.ImRepliesRequest
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImCloseResponse
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImHistoryResponse
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImListResponse
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImMarkResponse
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImOpenResponse
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImRepliesResponse
import com.kreait.slack.api.contract.jackson.group.im.sample
import com.kreait.slack.api.spring.DynamicGroupTests
import com.kreait.slack.api.spring.MetaInfo
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.springframework.web.client.RestTemplate


@DisplayName("IM MethodGroup")
class MethodInvocationTest {

    protected lateinit var mockTemplate: RestTemplate


    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @TestFactory
    @DisplayName("")
    fun methodInvocations(): List<DynamicTest> {
        return DynamicGroupTests.methodInvocations(testCases(), mockTemplate)
    }

    private fun testCases() = listOf(
            MetaInfo("im.close", SuccessfulImCloseResponse.sample(), ErrorImCloseResponse.sample(), ImCloseRequest.sample(), SpringImCloseMethod("", mockTemplate)),
            MetaInfo("im.history", SuccessfulImHistoryResponse.sample(), ErrorImHistoryResponse.sample(), ImHistoryRequest.sample(), SpringImHistoryMethod("", mockTemplate)),
            MetaInfo("im.list", SuccessfulImListResponse.sample(), ErrorImListResponse.sample(), ImListRequest.sample(), SpringImListMethod("", mockTemplate)),
            MetaInfo("im.mark", SuccessfulImMarkResponse.sample(), ErrorImMarkResponse.sample(), ImMarkRequest.sample(), SpringImMarkMethod("", mockTemplate)),
            MetaInfo("im.open", SuccessfulImOpenResponse.sample(), ErrorImOpenResponse.sample(), ImOpenRequest.sample(), SpringImOpenMethod("", mockTemplate)),
            MetaInfo("im.replies", SuccessfulImRepliesResponse.sample(), ErrorImRepliesResponse.sample(), ImRepliesRequest.sample(), SpringImRepliesMethod("", mockTemplate))
            )


}
