package io.hndrs.slack.api.spring.group.conversations


import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationArchiveResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationArchiveResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationArchiveResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.conversations.ConversationsArchiveMethod
import io.hndrs.slack.api.group.conversations.ConversationsMethodGroup
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [ConversationsMethodGroup.archive]
 */
@Suppress("UNCHECKED_CAST")
class SpringConversationsArchiveMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate()
) : io.hndrs.slack.api.group.conversations.ConversationsArchiveMethod() {

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulConversationArchiveResponse, ErrorConversationArchiveResponse> {
        val response = SlackRequestBuilder<ConversationArchiveResponse>(authToken, restTemplate)
            .with(this.params)
            .toMethod("conversations.archive")
            .returnAsType(ConversationArchiveResponse::class.java)
            .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulConversationArchiveResponse -> {
                val responseEntity = response.body as SuccessfulConversationArchiveResponse
                this.onSuccess?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(success = responseEntity)
            }
            is ErrorConversationArchiveResponse -> {
                val responseEntity = response.body as ErrorConversationArchiveResponse
                this.onFailure?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(failure = responseEntity)
            }
        }
    }
}
