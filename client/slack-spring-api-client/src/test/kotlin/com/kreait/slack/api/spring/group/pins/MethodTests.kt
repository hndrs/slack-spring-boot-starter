package com.kreait.slack.api.spring.group.pins

import com.kreait.slack.api.contract.jackson.group.pins.ErrorPinsAddResponse
import com.kreait.slack.api.contract.jackson.group.pins.ErrorPinsListResponse
import com.kreait.slack.api.contract.jackson.group.pins.ErrorPinsRemoveResponse
import com.kreait.slack.api.contract.jackson.group.pins.PinsAddRequest
import com.kreait.slack.api.contract.jackson.group.pins.PinsListRequest
import com.kreait.slack.api.contract.jackson.group.pins.PinsRemoveRequest
import com.kreait.slack.api.contract.jackson.group.pins.SuccessfulPinsAddResponse
import com.kreait.slack.api.contract.jackson.group.pins.SuccessfulPinsListResponse
import com.kreait.slack.api.contract.jackson.group.pins.SuccessfulPinsRemoveResponse
import com.kreait.slack.api.contract.jackson.group.pins.sample
import com.kreait.slack.api.spring.DynamicGroupTests
import com.kreait.slack.api.spring.MetaInfo
import com.kreait.slack.api.spring.group.RestTemplateFactory
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
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @TestFactory
    @DisplayName("")
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
