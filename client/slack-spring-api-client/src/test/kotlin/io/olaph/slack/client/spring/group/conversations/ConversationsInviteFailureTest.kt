package io.olaph.slack.client.spring.group.conversations

import com.fasterxml.jackson.databind.ObjectMapper
import io.olaph.slack.client.spring.TEST_LOG
import io.olaph.slack.dto.jackson.group.conversations.ConversationsInviteRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationInviteResponse
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.test.web.client.ExpectedCount
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.test.web.client.match.MockRestRequestMatchers.method
import org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo
import org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess
import org.springframework.web.client.RestTemplate
import java.net.URI

class ConversationsInviteFailureTest() {

    @Test
    @DisplayName("conversations.invite Failure")
    fun closeConversation() {
        val mapper = Jackson2ObjectMapperBuilder.json().build<ObjectMapper>()
        val mockTemplate = RestTemplate()

        val value = ErrorConversationInviteResponse(false, "")
        println(value.toString())
        val response = mapper.writeValueAsString(
                value)

        val mockServer = MockRestServiceServer.createServer(mockTemplate);
        mockServer.expect(ExpectedCount.once(),
                requestTo(URI("https://slack.com/api/conversations.invite")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(response)
                )

        DefaultConversationsInviteMethod("", mockTemplate)
                .with(ConversationsInviteRequest("channel_id", listOf("user_id")))
                .onFailure { TEST_LOG.info("{}", it) }
                .onSuccess { TEST_LOG.info("{}", it) }
                .invoke()

        mockServer.verify()
    }
}
