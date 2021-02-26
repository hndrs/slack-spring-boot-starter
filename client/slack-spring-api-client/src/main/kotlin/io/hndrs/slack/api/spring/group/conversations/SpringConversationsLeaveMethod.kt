package io.hndrs.slack.api.spring.group.conversations


import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationsLeaveResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationLeaveResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationLeaveResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.conversations.ConversationsLeaveMethod
import io.hndrs.slack.api.group.conversations.ConversationsMethodGroup
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [ConversationsMethodGroup.leave]
 */
@Suppress("UNCHECKED_CAST")
class SpringConversationsLeaveMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate()
) : io.hndrs.slack.api.group.conversations.ConversationsLeaveMethod() {

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulConversationLeaveResponse, ErrorConversationLeaveResponse> {
        val response = SlackRequestBuilder<ConversationsLeaveResponse>(authToken, restTemplate)
            .with(this.params)
            .toMethod("conversations.leave")
            .returnAsType(ConversationsLeaveResponse::class.java)
            .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulConversationLeaveResponse -> {
                val responseEntity = response.body as SuccessfulConversationLeaveResponse
                this.onSuccess?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(success = responseEntity)
            }
            is ErrorConversationLeaveResponse -> {
                val responseEntity = response.body as ErrorConversationLeaveResponse
                this.onFailure?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(failure = responseEntity)
            }
        }
    }
}


