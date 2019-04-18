package io.olaph.slack.client.spring.group.chat

import io.olaph.slack.client.ErrorResponseException
import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.chat.ChatDeleteMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.chat.ErrorChatDeleteResponse
import io.olaph.slack.dto.jackson.group.chat.SlackDeleteResponse
import io.olaph.slack.dto.jackson.group.chat.SuccessfulChatDeleteResponse
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultDeleteMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ChatDeleteMethod() {

    override fun request(): ApiCallResult<SuccessfulChatDeleteResponse, ErrorChatDeleteResponse> {
        val response = SlackRequestBuilder<SlackDeleteResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("chat.delete")
                .returnAsType(SlackDeleteResponse::class.java)
                .postWithJsonBody()

        if (!response.statusCode.is2xxSuccessful) {
            throw ErrorResponseException(this::class, response.statusCode.name)
        }

        return when (response.body!!) {
            is SuccessfulChatDeleteResponse -> {
                val responseEntity = response.body as SuccessfulChatDeleteResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorChatDeleteResponse -> {
                val responseEntity = response.body as ErrorChatDeleteResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
