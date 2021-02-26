package io.hndrs.slack.api.spring.group.dialog

import io.hndrs.slack.api.contract.jackson.group.chat.ChatDeleteRequest
import io.hndrs.slack.api.contract.jackson.group.chat.sample
import io.hndrs.slack.api.contract.jackson.group.dialog.ErrorOpenDialogResponse
import io.hndrs.slack.api.contract.jackson.group.dialog.SuccessfulOpenDialogResponse
import io.hndrs.slack.api.contract.jackson.group.dialog.sample
import io.hndrs.slack.api.spring.DynamicGroupTests
import io.hndrs.slack.api.spring.MetaInfo
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.springframework.web.client.RestTemplate


@DisplayName("Dialog MethodGroup")
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
            MetaInfo("dialog.open", SuccessfulOpenDialogResponse.sample(), ErrorOpenDialogResponse.sample(), ChatDeleteRequest.sample(), SpringDialogOpenMethod("", mockTemplate))
    )


}
