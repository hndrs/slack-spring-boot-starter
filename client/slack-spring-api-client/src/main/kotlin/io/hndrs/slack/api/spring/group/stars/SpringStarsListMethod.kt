package io.hndrs.slack.api.spring.group.stars

import io.hndrs.slack.api.contract.jackson.group.stars.ErrorStarsListResponse
import io.hndrs.slack.api.contract.jackson.group.stars.StarsListResponse
import io.hndrs.slack.api.contract.jackson.group.stars.SuccessfulStarsListResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.stars.StarsListMethod
import io.hndrs.slack.api.group.stars.StarsMethodGroup
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [StarsMethodGroup.list]
 */
class SpringStarsListMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()
) : StarsListMethod() {
    override fun request(): ApiCallResult<SuccessfulStarsListResponse, ErrorStarsListResponse> {

        val response = SlackRequestBuilder<StarsListResponse>(authToken, restTemplate)
            .with(this.params)
            .toMethod("stars.list")
            .returnAsType(StarsListResponse::class.java)
            .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulStarsListResponse -> {
                val responseEntity = response.body as SuccessfulStarsListResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }

            is ErrorStarsListResponse -> {
                val responseEntity = response.body as ErrorStarsListResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
