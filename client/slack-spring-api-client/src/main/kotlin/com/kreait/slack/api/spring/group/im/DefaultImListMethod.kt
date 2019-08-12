package com.kreait.slack.api.spring.group.im


import com.kreait.slack.api.contract.jackson.group.im.ErrorImListResponse
import com.kreait.slack.api.contract.jackson.group.im.SlackImListResponse
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImListResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.im.ImListMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * https://api.slack.com/methods/im.List
 */
@Suppress("UNCHECKED_CAST")
class DefaultImListMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ImListMethod() {

    override fun request(): ApiCallResult<SuccessfulImListResponse, ErrorImListResponse> {
        val response = SlackRequestBuilder<SlackImListResponse>(authToken, restTemplate)
                .toMethod("im.list")
                .returnAsType(SlackImListResponse::class.java)
                .postUrlEncoded(this.params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulImListResponse -> {
                val responseEntity = response.body as SuccessfulImListResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorImListResponse -> {
                val responseEntity = response.body as ErrorImListResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
