package com.kreait.slack.api.spring.group.chat


import com.kreait.slack.api.contract.jackson.group.chat.ErrorChatDeleteResponse
import com.kreait.slack.api.contract.jackson.group.chat.ChatDeleteResponse
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulChatDeleteResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.chat.ChatDeleteMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultDeleteMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ChatDeleteMethod() {

    override fun request(): ApiCallResult<SuccessfulChatDeleteResponse, ErrorChatDeleteResponse> {
        val response = SlackRequestBuilder<ChatDeleteResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("chat.delete")
                .returnAsType(ChatDeleteResponse::class.java)
                .postWithJsonBody()

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
