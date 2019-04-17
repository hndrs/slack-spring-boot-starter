package io.olaph.slack.client.spring.group.im

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.im.ErrorImRepliesResponse
import io.olaph.slack.dto.jackson.group.im.SlackImRepliesRequest
import io.olaph.slack.dto.jackson.group.im.SuccessfulImRepliesResponse
import io.olaph.slack.dto.jackson.group.im.sample
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

internal class DefaultImRepliesMethodTest {
    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("Im.replies Failure")
    fun imRepliesFailure() {
        val response = ErrorImRepliesResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "im.replies?channel=channeId&thread_ts=1234567890.123456")
        val verifier = Verifier(response)

        DefaultImRepliesMethod("", mockTemplate)
                .with(SlackImRepliesRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("Im.replies Success")
    fun imRepliesSuccess() {
        val response = SuccessfulImRepliesResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "im.replies?channel=channeId&thread_ts=1234567890.123456")
        val verifier = Verifier(response)

        DefaultImRepliesMethod("", mockTemplate)
                .with(SlackImRepliesRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
