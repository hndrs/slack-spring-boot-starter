package io.olaph.slack.client.spring.group.usergroups

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.usergroups.ErrorUsergroupsUpdateResponse
import io.olaph.slack.dto.jackson.group.usergroups.SlackUsergroupsUpdateRequest
import io.olaph.slack.dto.jackson.group.usergroups.SuccessfulUsergroupsUpdateResponse
import io.olaph.slack.dto.jackson.group.usergroups.sample
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class UsergroupsUpdateMethodTest {
    lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @DisplayName("usergroups.update success")
    @Test
    fun usergroupsUpdateSuccess() {
        val response = SuccessfulUsergroupsUpdateResponse.sample()
        val mockServerHelper = MockServerHelper.buildMockRestServer(mockTemplate, response, "usergroups.update")
        val verifier = Verifier(response)

        DefaultUsergroupsUpdateMethod("", mockTemplate)
                .with(SlackUsergroupsUpdateRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()

        mockServerHelper.verify()
        verifier.verify()
    }

    @DisplayName("usergroups.update failure")
    @Test
    fun usergroupsUpdateFailure() {
        val response = ErrorUsergroupsUpdateResponse.sample()
        val mockServerHelper = MockServerHelper.buildMockRestServer(mockTemplate, response, "usergroups.update")
        val verifier = Verifier(response)

        DefaultUsergroupsUpdateMethod("", mockTemplate)
                .with(SlackUsergroupsUpdateRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()

        mockServerHelper.verify()
        verifier.verify()
    }
}