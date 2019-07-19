package io.olaph.slack.client.spring.group.im

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.im.ErrorImListResponse
import io.olaph.slack.dto.jackson.group.im.SlackImListRequest
import io.olaph.slack.dto.jackson.group.im.SuccessfulImListResponse
import io.olaph.slack.dto.jackson.group.im.sample
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

internal class DefaultImListMethodTest {
    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("Im.list Failure")
    fun imListFailure() {
        val response = ErrorImListResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "im.list", response)
        val verifier = Verifier(response)
        DefaultImListMethod("", mockTemplate)
                .with(SlackImListRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("Im.list Success")
    fun imListSuccess() {
        val response = SuccessfulImListResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "im.list", response)
        val verifier = Verifier(response)

        DefaultImListMethod("", mockTemplate)
                .with(SlackImListRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
