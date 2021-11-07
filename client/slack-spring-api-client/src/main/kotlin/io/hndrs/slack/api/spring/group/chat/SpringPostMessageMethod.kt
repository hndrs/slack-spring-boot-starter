package io.hndrs.slack.api.spring.group.chat


import io.hndrs.slack.api.contract.jackson.group.chat.ErrorPostMessageResponse
import io.hndrs.slack.api.contract.jackson.group.chat.PostMessageResponse
import io.hndrs.slack.api.contract.jackson.group.chat.SuccessfulPostMessageResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.chat.ChatMethodGroup
import io.hndrs.slack.api.group.chat.ChatPostMessageMethod
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
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
