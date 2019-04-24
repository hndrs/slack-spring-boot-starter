package io.olaph.slack.client.spring.group.conversations


import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.conversations.ConversationsUnarchiveMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.conversations.ConversationUnarchiveResponse
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationUnarchiveResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationUnarchiveResponse
import org.springframework.web.client.RestTemplate

@Suppress("UNCHECKED_CAST")
class DefaultConversationsUnarchiveMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ConversationsUnarchiveMethod() {

    override fun request(): ApiCallResult<SuccessfulConversationUnarchiveResponse, ErrorConversationUnarchiveResponse> {
        val response = SlackRequestBuilder<ConversationUnarchiveResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("conversations.unarchive")
                .returnAsType(ConversationUnarchiveResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulConversationUnarchiveResponse -> {
                val responseEntity = response.body as SuccessfulConversationUnarchiveResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorConversationUnarchiveResponse -> {
                val responseEntity = response.body as ErrorConversationUnarchiveResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
