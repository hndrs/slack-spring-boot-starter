package io.olaph.slack.client.spring.group.auth

import io.olaph.slack.client.ErrorResponseException
import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.auth.AuthTestMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.auth.ErrorAuthTestResponse
import io.olaph.slack.dto.jackson.group.auth.SlackAuthTestResponse
import io.olaph.slack.dto.jackson.group.auth.SuccessfulAuthTestResponse
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultTestMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : AuthTestMethod() {

    override fun request(): ApiCallResult<SuccessfulAuthTestResponse, ErrorAuthTestResponse> {
        val response = SlackRequestBuilder<SlackAuthTestResponse>(authToken, restTemplate)
                .toMethod("auth.test")
                .returnAsType(SlackAuthTestResponse::class.java)
                .postWithJsonBody()

        if (!response.statusCode.is2xxSuccessful) {
            throw ErrorResponseException(this::class, response.statusCode.name)
        }

        return when (response.body!!) {
            is SuccessfulAuthTestResponse -> {
                val responseEntity = response.body as SuccessfulAuthTestResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorAuthTestResponse -> {
                val responseEntity = response.body as ErrorAuthTestResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
