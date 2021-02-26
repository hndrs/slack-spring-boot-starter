package io.hndrs.slack.api.spring.group.stars

import io.hndrs.slack.api.contract.jackson.group.stars.ErrorStarsAddResponse
import io.hndrs.slack.api.contract.jackson.group.stars.ErrorStarsListResponse
import io.hndrs.slack.api.contract.jackson.group.stars.ErrorStarsRemoveResponse
import io.hndrs.slack.api.contract.jackson.group.stars.StarsAddRequest
import io.hndrs.slack.api.contract.jackson.group.stars.StarsListRequest
import io.hndrs.slack.api.contract.jackson.group.stars.StarsRemoveRequest
import io.hndrs.slack.api.contract.jackson.group.stars.SuccessfulStarsAddResponse
import io.hndrs.slack.api.contract.jackson.group.stars.SuccessfulStarsListResponse
import io.hndrs.slack.api.contract.jackson.group.stars.SuccessfulStarsRemoveResponse
import io.hndrs.slack.api.contract.jackson.group.stars.sample
import io.hndrs.slack.api.spring.DynamicGroupTests
import io.hndrs.slack.api.spring.MetaInfo
import io.hndrs.slack.api.spring.group.RestTemplateFactory
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
        mockTemplate = io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate()
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
