package io.hndrs.slack.api.spring.group.stars

import io.hndrs.slack.api.contract.jackson.group.stars.ErrorStarsRemoveResponse
import io.hndrs.slack.api.contract.jackson.group.stars.StarsRemoveResponse
import io.hndrs.slack.api.contract.jackson.group.stars.SuccessfulStarsRemoveResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.stars.StarsMethodGroup
import io.hndrs.slack.api.group.stars.StarsRemoveMethod
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [StarsMethodGroup.remove]
 */
class SpringStarsRemoveMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()
) : StarsRemoveMethod() {
    override fun request(): ApiCallResult<SuccessfulStarsRemoveResponse, ErrorStarsRemoveResponse> {

        val response = SlackRequestBuilder<StarsRemoveResponse>(authToken, restTemplate)
            .with(this.params)
            .toMethod("stars.remove")
            .returnAsType(StarsRemoveResponse::class.java)
            .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulStarsRemoveResponse -> {
                val responseEntity = response.body as SuccessfulStarsRemoveResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }

            is ErrorStarsRemoveResponse -> {
                val responseEntity = response.body as ErrorStarsRemoveResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
