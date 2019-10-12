package com.kreait.slack.api.spring.group.pins

import com.kreait.slack.api.contract.jackson.group.pins.ErrorPinsRemoveResponse
import com.kreait.slack.api.contract.jackson.group.pins.PinsRemoveResponse
import com.kreait.slack.api.contract.jackson.group.pins.SuccessfulPinsRemoveResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.pins.PinsRemoveMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [PinsMethodGroup.remove]
 */
class SpringPinsRemoveMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()
) : PinsRemoveMethod() {
    override fun request(): ApiCallResult<SuccessfulPinsRemoveResponse, ErrorPinsRemoveResponse> {

        val response = SlackRequestBuilder<PinsRemoveResponse>(authToken, restTemplate)
            .with(this.params)
            .toMethod("pins.remove")
            .returnAsType(PinsRemoveResponse::class.java)
            .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulPinsRemoveResponse -> {
                val responseEntity = response.body as SuccessfulPinsRemoveResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }

            is ErrorPinsRemoveResponse -> {
                val responseEntity = response.body as ErrorPinsRemoveResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
