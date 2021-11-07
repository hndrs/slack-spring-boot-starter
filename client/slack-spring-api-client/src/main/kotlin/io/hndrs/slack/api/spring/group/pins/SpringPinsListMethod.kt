package io.hndrs.slack.api.spring.group.pins

import io.hndrs.slack.api.contract.jackson.group.pins.ErrorPinsListResponse
import io.hndrs.slack.api.contract.jackson.group.pins.PinsListResponse
import io.hndrs.slack.api.contract.jackson.group.pins.SuccessfulPinsListResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.pins.PinsListMethod
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
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
