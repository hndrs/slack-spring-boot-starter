package com.kreait.slack.api.spring.group.pins

import com.kreait.slack.api.contract.jackson.group.pins.ErrorPinsListResponse
import com.kreait.slack.api.contract.jackson.group.pins.PinsListResponse
import com.kreait.slack.api.contract.jackson.group.pins.SuccessfulPinsListResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.pins.PinsListMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [PinsMethodGroup.list]
 */
class SpringPinsListMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()
) : PinsListMethod() {
    override fun request(): ApiCallResult<SuccessfulPinsListResponse, ErrorPinsListResponse> {

        val response = SlackRequestBuilder<PinsListResponse>(authToken, restTemplate)
            .with(this.params)
            .toMethod("pins.list")
            .returnAsType(PinsListResponse::class.java)
            .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulPinsListResponse -> {
                val responseEntity = response.body as SuccessfulPinsListResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }

            is ErrorPinsListResponse -> {
                val responseEntity = response.body as ErrorPinsListResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
