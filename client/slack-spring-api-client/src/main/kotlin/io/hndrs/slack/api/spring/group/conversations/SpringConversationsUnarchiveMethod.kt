package io.hndrs.slack.api.spring.group.conversations


import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationUnarchiveResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationUnarchiveResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationUnarchiveResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.conversations.ConversationsMethodGroup
import io.hndrs.slack.api.group.conversations.ConversationsUnarchiveMethod
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [ConversationsMethodGroup.unarchive]
 */
@Suppress("UNCHECKED_CAST")
class SpringConversationsUnarchiveMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()
) : ConversationsUnarchiveMethod() {

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
