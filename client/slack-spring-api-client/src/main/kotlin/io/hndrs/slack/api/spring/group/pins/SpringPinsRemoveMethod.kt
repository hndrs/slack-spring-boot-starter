package io.hndrs.slack.api.spring.group.pins

import io.hndrs.slack.api.contract.jackson.group.pins.ErrorPinsRemoveResponse
import io.hndrs.slack.api.contract.jackson.group.pins.PinsRemoveResponse
import io.hndrs.slack.api.contract.jackson.group.pins.SuccessfulPinsRemoveResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.pins.PinsRemoveMethod
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
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
