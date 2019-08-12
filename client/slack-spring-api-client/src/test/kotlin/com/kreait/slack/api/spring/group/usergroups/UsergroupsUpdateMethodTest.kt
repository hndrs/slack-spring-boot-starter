package com.kreait.slack.api.spring.group.usergroups

import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorUpdateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.UpdateRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulUpdateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
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
        val response = SuccessfulUpdateResponse.sample()
        val mockServerHelper = MockServerHelper.buildMockRestServer(mockTemplate, "usergroups.update", response)
        val verifier = Verifier(response)

        DefaultUsergroupsUpdateMethod("", mockTemplate)
                .with(UpdateRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()

        mockServerHelper.verify()
        verifier.verify()
    }

    @DisplayName("usergroups.update failure")
    @Test
    fun usergroupsUpdateFailure() {
        val response = ErrorUpdateResponse.sample()
        val mockServerHelper = MockServerHelper.buildMockRestServer(mockTemplate, "usergroups.update", response)
        val verifier = Verifier(response)

        DefaultUsergroupsUpdateMethod("", mockTemplate)
                .with(UpdateRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()

        mockServerHelper.verify()
        verifier.verify()
    }
}
