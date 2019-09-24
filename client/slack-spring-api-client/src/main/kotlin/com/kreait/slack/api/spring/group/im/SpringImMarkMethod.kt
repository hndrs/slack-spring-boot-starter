package com.kreait.slack.api.spring.group.im

import com.kreait.slack.api.contract.jackson.group.im.ErrorImMarkResponse
import com.kreait.slack.api.contract.jackson.group.im.ImMarkResponse
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImMarkResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.im.ImMarkMethod
import com.kreait.slack.api.group.im.ImMethodGroup
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * Spring based implementation of [ImMethodGroup.mark]
 */
@Suppress("UNCHECKED_CAST")
class SpringImMarkMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ImMarkMethod() {

    override fun request(): ApiCallResult<SuccessfulImMarkResponse, ErrorImMarkResponse> {
        val response = SlackRequestBuilder<ImMarkResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("im.mark")
                .returnAsType(ImMarkResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulImMarkResponse -> {
                val responseEntity = response.body as SuccessfulImMarkResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorImMarkResponse -> {
                val responseEntity = response.body as ErrorImMarkResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
