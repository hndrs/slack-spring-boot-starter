package com.kreait.slack.api.spring.group.chat


import com.kreait.slack.api.contract.jackson.group.chat.ChatGetPermalinkResponse
import com.kreait.slack.api.contract.jackson.group.chat.ErrorChatGetPermalinkResponse
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulChatGetPermalinkResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.chat.ChatGetPermalinkMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultGetPermalinkMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ChatGetPermalinkMethod() {

    override fun request(): ApiCallResult<SuccessfulChatGetPermalinkResponse, ErrorChatGetPermalinkResponse> {
        val response = SlackRequestBuilder<ChatGetPermalinkResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("chat.getPermalink")
                .returnAsType(ChatGetPermalinkResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulChatGetPermalinkResponse -> {
                val responseEntity = response.body as SuccessfulChatGetPermalinkResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorChatGetPermalinkResponse -> {
                val responseEntity = response.body as ErrorChatGetPermalinkResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
