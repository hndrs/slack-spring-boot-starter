package io.hndrs.slack.api.spring.group.stars

import io.hndrs.slack.api.contract.jackson.group.stars.ErrorStarsAddResponse
import io.hndrs.slack.api.contract.jackson.group.stars.StarsAddResponse
import io.hndrs.slack.api.contract.jackson.group.stars.SuccessfulStarsAddResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.stars.StarsAddMethod
import io.hndrs.slack.api.group.stars.StarsMethodGroup
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [StarsMethodGroup.add]
 */
class SpringStarsAddMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate()
) : io.hndrs.slack.api.group.stars.StarsAddMethod() {
    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulStarsAddResponse, ErrorStarsAddResponse> {

        val response = SlackRequestBuilder<StarsAddResponse>(authToken, restTemplate)
            .with(this.params)
            .toMethod("stars.add")
            .returnAsType(StarsAddResponse::class.java)
            .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulStarsAddResponse -> {
                val responseEntity = response.body as SuccessfulStarsAddResponse
                this.onSuccess?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(success = responseEntity)
            }

            is ErrorStarsAddResponse -> {
                val responseEntity = response.body as ErrorStarsAddResponse
                this.onFailure?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(failure = responseEntity)
            }
        }
    }
}
