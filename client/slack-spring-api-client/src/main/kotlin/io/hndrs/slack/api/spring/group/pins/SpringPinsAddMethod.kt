package io.hndrs.slack.api.spring.group.pins

import io.hndrs.slack.api.contract.jackson.group.pins.ErrorPinsAddResponse
import io.hndrs.slack.api.contract.jackson.group.pins.PinsAddResponse
import io.hndrs.slack.api.contract.jackson.group.pins.SuccessfulPinsAddResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.pins.PinsAddMethod
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [PinsMethodGroup.add]
 */
class SpringPinsAddMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate()
) : io.hndrs.slack.api.group.pins.PinsAddMethod() {
    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulPinsAddResponse, ErrorPinsAddResponse> {

        val response = SlackRequestBuilder<PinsAddResponse>(authToken, restTemplate)
            .with(this.params)
            .toMethod("pins.add")
            .returnAsType(PinsAddResponse::class.java)
            .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulPinsAddResponse -> {
                val responseEntity = response.body as SuccessfulPinsAddResponse
                this.onSuccess?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(success = responseEntity)
            }

            is ErrorPinsAddResponse -> {
                val responseEntity = response.body as ErrorPinsAddResponse
                this.onFailure?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(failure = responseEntity)
            }
        }
    }
}
