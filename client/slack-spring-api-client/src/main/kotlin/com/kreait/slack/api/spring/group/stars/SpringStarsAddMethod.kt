package com.kreait.slack.api.spring.group.stars

import com.kreait.slack.api.contract.jackson.group.stars.ErrorStarsAddResponse
import com.kreait.slack.api.contract.jackson.group.stars.StarsAddResponse
import com.kreait.slack.api.contract.jackson.group.stars.SuccessfulStarsAddResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.stars.StarsAddMethod
import com.kreait.slack.api.group.stars.StarsMethodGroup
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [StarsMethodGroup.add]
 */
class SpringStarsAddMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()
) : StarsAddMethod() {
    override fun request(): ApiCallResult<SuccessfulStarsAddResponse, ErrorStarsAddResponse> {

        val response = SlackRequestBuilder<StarsAddResponse>(authToken, restTemplate)
            .with(this.params)
            .toMethod("stars.add")
            .returnAsType(StarsAddResponse::class.java)
            .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulStarsAddResponse -> {
                val responseEntity = response.body as SuccessfulStarsAddResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }

            is ErrorStarsAddResponse -> {
                val responseEntity = response.body as ErrorStarsAddResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
