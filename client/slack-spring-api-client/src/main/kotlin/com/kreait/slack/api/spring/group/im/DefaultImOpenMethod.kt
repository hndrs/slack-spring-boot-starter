package com.kreait.slack.api.spring.group.im


import com.kreait.slack.api.contract.jackson.group.im.ErrorImOpenResponse
import com.kreait.slack.api.contract.jackson.group.im.SlackImOpenResponse
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImOpenResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.im.ImOpenMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * https://api.slack.com/methods/im.open
 */
@Suppress("UNCHECKED_CAST")
class DefaultImOpenMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ImOpenMethod() {

    override fun request(): ApiCallResult<SuccessfulImOpenResponse, ErrorImOpenResponse> {
        val response = SlackRequestBuilder<SlackImOpenResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("im.open")
                .returnAsType(SlackImOpenResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulImOpenResponse -> {
                val responseEntity = response.body as SuccessfulImOpenResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorImOpenResponse -> {
                val responseEntity = response.body as ErrorImOpenResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
