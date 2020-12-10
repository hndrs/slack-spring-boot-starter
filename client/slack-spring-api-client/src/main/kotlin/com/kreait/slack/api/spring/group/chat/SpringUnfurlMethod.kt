package com.kreait.slack.api.spring.group.chat


import com.kreait.slack.api.contract.jackson.group.chat.ChatUnfurlResponse
import com.kreait.slack.api.contract.jackson.group.chat.ErrorChatUnfurlResponse
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulChatUnfurlResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.chat.ChatMethodGroup
import com.kreait.slack.api.group.chat.ChatUnfurlMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * Spring based implementation of [ChatMethodGroup.unfurl]
 */
@Suppress("UNCHECKED_CAST")
class SpringUnfurlMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()
) : ChatUnfurlMethod() {

    override fun request(): ApiCallResult<SuccessfulChatUnfurlResponse, ErrorChatUnfurlResponse> {
        val response = SlackRequestBuilder<ChatUnfurlResponse>(authToken, restTemplate)
            .with(this.params)
            .toMethod("chat.unfurl")
            .returnAsType(ChatUnfurlResponse::class.java)
            .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulChatUnfurlResponse -> {
                val responseEntity = response.body as SuccessfulChatUnfurlResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorChatUnfurlResponse -> {
                val responseEntity = response.body as ErrorChatUnfurlResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
