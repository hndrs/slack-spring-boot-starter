package com.kreait.slack.api.spring.group.stars

import com.kreait.slack.api.contract.jackson.group.stars.ErrorStarsAddResponse
import com.kreait.slack.api.contract.jackson.group.stars.ErrorStarsListResponse
import com.kreait.slack.api.contract.jackson.group.stars.ErrorStarsRemoveResponse
import com.kreait.slack.api.contract.jackson.group.stars.StarsAddRequest
import com.kreait.slack.api.contract.jackson.group.stars.StarsListRequest
import com.kreait.slack.api.contract.jackson.group.stars.StarsRemoveRequest
import com.kreait.slack.api.contract.jackson.group.stars.SuccessfulStarsAddResponse
import com.kreait.slack.api.contract.jackson.group.stars.SuccessfulStarsListResponse
import com.kreait.slack.api.contract.jackson.group.stars.SuccessfulStarsRemoveResponse
import com.kreait.slack.api.contract.jackson.group.stars.sample
import com.kreait.slack.api.spring.DynamicGroupTests
import com.kreait.slack.api.spring.MetaInfo
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.springframework.web.client.RestTemplate

@DisplayName("STARS MethodGroup")
class MethodInvocationTest {

    protected lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @TestFactory
    fun methodInvocations(): List<DynamicTest> {
        return DynamicGroupTests.methodInvocations(testCases(), mockTemplate)
    }

    private fun testCases() = listOf(
        MetaInfo(
            "stars.add",
            SuccessfulStarsAddResponse.sample(),
            ErrorStarsAddResponse.sample(),
            StarsAddRequest.sample(),
            SpringStarsAddMethod("", mockTemplate)
        ),
        MetaInfo(
            "stars.list",
            SuccessfulStarsListResponse.sample(),
            ErrorStarsListResponse.sample(),
            StarsListRequest.sample(),
            SpringStarsListMethod("", mockTemplate)
        ),
        MetaInfo(
            "stars.remove",
            SuccessfulStarsRemoveResponse.sample(),
            ErrorStarsRemoveResponse.sample(),
            StarsRemoveRequest.sample(),
            SpringStarsRemoveMethod("", mockTemplate)
        )
    )

}
