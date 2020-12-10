package com.kreait.slack.api.spring.group.chat


import com.kreait.slack.api.contract.jackson.group.chat.ErrorPostMessageResponse
import com.kreait.slack.api.contract.jackson.group.chat.PostMessageResponse
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulPostMessageResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.chat.ChatMethodGroup
import com.kreait.slack.api.group.chat.ChatPostMessageMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * Spring based implementation of [ChatMethodGroup.postMessage]
 */
@Suppress("UNCHECKED_CAST")
class SpringPostMessageMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()
) : ChatPostMessageMethod() {

    override fun request(): ApiCallResult<SuccessfulPostMessageResponse, ErrorPostMessageResponse> {
        val response = SlackRequestBuilder<PostMessageResponse>(authToken, restTemplate)
            .with(this.params)
            .toMethod("chat.postMessage")
            .returnAsType(PostMessageResponse::class.java)
            .postWithJsonBody()
        return when (response.body!!) {
            is SuccessfulPostMessageResponse -> {
                val responseEntity = response.body as SuccessfulPostMessageResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorPostMessageResponse -> {
                val responseEntity = response.body as ErrorPostMessageResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
