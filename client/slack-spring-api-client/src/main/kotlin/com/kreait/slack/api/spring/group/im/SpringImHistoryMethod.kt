package com.kreait.slack.api.spring.group.im

import com.kreait.slack.api.contract.jackson.group.im.ErrorImHistoryResponse
import com.kreait.slack.api.contract.jackson.group.im.ImHistoryResponse
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImHistoryResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.im.ImHistoryMethod
import com.kreait.slack.api.group.im.ImMethodGroup
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * Spring based implementation of [ImMethodGroup.history]
 */
@Suppress("UNCHECKED_CAST")
class SpringImHistoryMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ImHistoryMethod() {

    override fun request(): ApiCallResult<SuccessfulImHistoryResponse, ErrorImHistoryResponse> {
        val response = SlackRequestBuilder<ImHistoryResponse>(authToken, restTemplate)
                .toMethod("im.history")
                .returnAsType(ImHistoryResponse::class.java)
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
