package io.olaph.slack.client.spring.group.conversations

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.conversations.ConversationsSetTopicMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.conversations.ConversationSetTopicResponse
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationSetTopicResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationSetTopicResponse
import org.springframework.web.client.RestTemplate

@Suppress("UNCHECKED_CAST")
class DefaultConversationsSetTopicMethod(private val authToken: String,
                                         private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate())
    : ConversationsSetTopicMethod() {

    override fun request(): ApiCallResult<SuccessfulConversationSetTopicResponse, ErrorConversationSetTopicResponse> {

        val response = SlackRequestBuilder<ConversationSetTopicResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("conversations.setTopic")
                .returnAsType(ConversationSetTopicResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulConversationSetTopicResponse -> {

                val responseEntity = response.body as SuccessfulConversationSetTopicResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }

            is ErrorConversationSetTopicResponse -> {

                val responseEntity = response.body as ErrorConversationSetTopicResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
