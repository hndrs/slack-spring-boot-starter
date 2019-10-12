package com.kreait.slack.api.spring.group.pins

import com.kreait.slack.api.contract.jackson.group.pins.ErrorPinsAddResponse
import com.kreait.slack.api.contract.jackson.group.pins.PinsAddResponse
import com.kreait.slack.api.contract.jackson.group.pins.SuccessfulPinsAddResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.pins.PinsAddMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [PinsMethodGroup.add]
 */
class SpringPinsAddMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()
) : PinsAddMethod() {
    override fun request(): ApiCallResult<SuccessfulPinsAddResponse, ErrorPinsAddResponse> {

        val response = SlackRequestBuilder<PinsAddResponse>(authToken, restTemplate)
            .with(this.params)
            .toMethod("pins.add")
            .returnAsType(PinsAddResponse::class.java)
            .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulPinsAddResponse -> {
                val responseEntity = response.body as SuccessfulPinsAddResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }

            is ErrorPinsAddResponse -> {
                val responseEntity = response.body as ErrorPinsAddResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
