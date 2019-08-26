package com.kreait.slack.api.test.group.im

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
import com.kreait.slack.api.test.DynamicMockGroupTests
import com.kreait.slack.api.test.MockMetaInfo
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class DynamicMockImTests {

    @TestFactory
    fun methodInvocations(): List<DynamicTest> = DynamicMockGroupTests.methodInvocations(testCases = testCases())

    private fun testCases() = listOf(
            MockMetaInfo(MockImHistoryMethod(), mock { }, SuccessfulImHistoryResponse.sample(), mock { }, ErrorImHistoryResponse.sample(), ImHistoryRequest.sample()),
            MockMetaInfo(MockImOpenMethod(), mock { }, SuccessfulImOpenResponse.sample(), mock { }, ErrorImOpenResponse.sample(), ImOpenRequest.sample()),
            MockMetaInfo(MockImListMethod(), mock { }, SuccessfulImListResponse.sample(), mock { }, ErrorImListResponse.sample(), ImListRequest.sample()),
            MockMetaInfo(MockImRepliesMethod(), mock { }, SuccessfulImRepliesResponse.sample(), mock { }, ErrorImRepliesResponse.sample(), ImRepliesRequest.sample()),
            MockMetaInfo(MockImCloseMethod(), mock { }, SuccessfulImCloseResponse.sample(), mock { }, ErrorImCloseResponse.sample(), ImCloseRequest.sample()),
            MockMetaInfo(MockImMarkMethod(), mock { }, SuccessfulImMarkResponse.sample(), mock { }, ErrorImMarkResponse.sample(), ImMarkRequest.sample())
    )
}