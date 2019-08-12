package com.kreait.slack.api.spring.group.im


import com.kreait.slack.api.contract.jackson.group.im.ErrorImCloseResponse
import com.kreait.slack.api.contract.jackson.group.im.ImCloseResponse
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImCloseResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.im.ImCloseMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * https://api.slack.com/methods/im.close
 */
@Suppress("UNCHECKED_CAST")
class DefaultImCloseMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ImCloseMethod() {
    override fun request(): ApiCallResult<SuccessfulImCloseResponse, ErrorImCloseResponse> {

        val response = SlackRequestBuilder<ImCloseResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("im.close")
                .returnAsType(ImCloseResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulImCloseResponse -> {
                val responseEntity = response.body as SuccessfulImCloseResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }

            is ErrorImCloseResponse -> {
                val responseEntity = response.body as ErrorImCloseResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
