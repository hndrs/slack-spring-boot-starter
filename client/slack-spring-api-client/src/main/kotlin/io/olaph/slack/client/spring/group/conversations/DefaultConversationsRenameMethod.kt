package io.olaph.slack.client.spring.group.conversations


import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.conversations.ConversationsRenameMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.conversations.ConversationsRenameResponse
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationsRenameResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationsRenameResponse
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultConversationsRenameMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ConversationsRenameMethod() {

    override fun request(): ApiCallResult<SuccessfulConversationsRenameResponse, ErrorConversationsRenameResponse> {
        val response = SlackRequestBuilder<ConversationsRenameResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("conversations.rename")
                .returnAsType(ConversationsRenameResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulConversationsRenameResponse -> {
                val responseEntity = response.body as SuccessfulConversationsRenameResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorConversationsRenameResponse -> {
                val responseEntity = response.body as ErrorConversationsRenameResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}


