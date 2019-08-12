package com.kreait.slack.api.spring.group.im

import com.kreait.slack.api.contract.jackson.group.im.ErrorImHistoryResponse
import com.kreait.slack.api.contract.jackson.group.im.SlackImHistoryResponse
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImHistoryResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.im.ImHistoryMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * https://api.slack.com/methods/im.history
 */
@Suppress("UNCHECKED_CAST")
class DefaultImHistoryMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ImHistoryMethod() {

    override fun request(): ApiCallResult<SuccessfulImHistoryResponse, ErrorImHistoryResponse> {
        val response = SlackRequestBuilder<SlackImHistoryResponse>(authToken, restTemplate)
                .toMethod("im.history")
                .returnAsType(SlackImHistoryResponse::class.java)
                .postUrlEncoded(this.params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulImHistoryResponse -> {
                val responseEntity = response.body as SuccessfulImHistoryResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorImHistoryResponse -> {
                val responseEntity = response.body as ErrorImHistoryResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
