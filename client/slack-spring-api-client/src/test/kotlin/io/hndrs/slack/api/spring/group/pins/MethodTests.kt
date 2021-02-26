package io.hndrs.slack.api.spring.group.pins

import io.hndrs.slack.api.contract.jackson.group.pins.ErrorPinsAddResponse
import io.hndrs.slack.api.contract.jackson.group.pins.ErrorPinsListResponse
import io.hndrs.slack.api.contract.jackson.group.pins.ErrorPinsRemoveResponse
import io.hndrs.slack.api.contract.jackson.group.pins.PinsAddRequest
import io.hndrs.slack.api.contract.jackson.group.pins.PinsListRequest
import io.hndrs.slack.api.contract.jackson.group.pins.PinsRemoveRequest
import io.hndrs.slack.api.contract.jackson.group.pins.SuccessfulPinsAddResponse
import io.hndrs.slack.api.contract.jackson.group.pins.SuccessfulPinsListResponse
import io.hndrs.slack.api.contract.jackson.group.pins.SuccessfulPinsRemoveResponse
import io.hndrs.slack.api.contract.jackson.group.pins.sample
import io.hndrs.slack.api.spring.DynamicGroupTests
import io.hndrs.slack.api.spring.MetaInfo
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.springframework.web.client.RestTemplate


@DisplayName("PINS MethodGroup")
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
            "pins.add",
            SuccessfulPinsAddResponse.sample(),
            ErrorPinsAddResponse.sample(),
            PinsAddRequest.sample(),
            SpringPinsAddMethod("", mockTemplate)
        ),
        MetaInfo(
            "pins.list",
            SuccessfulPinsListResponse.sample(),
            ErrorPinsListResponse.sample(),
            PinsListRequest.sample(),
            SpringPinsListMethod("", mockTemplate)
        ),
        MetaInfo(
            "pins.remove",
            SuccessfulPinsRemoveResponse.sample(),
            ErrorPinsRemoveResponse.sample(),
            PinsRemoveRequest.sample(),
            SpringPinsRemoveMethod("", mockTemplate)
        )
    )


}
