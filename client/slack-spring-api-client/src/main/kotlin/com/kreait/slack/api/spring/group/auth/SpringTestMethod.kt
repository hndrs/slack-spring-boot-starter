package com.kreait.slack.api.spring.group.auth


import com.kreait.slack.api.contract.jackson.group.auth.ErrorAuthTestResponse
import com.kreait.slack.api.contract.jackson.group.auth.AuthTestResponse
import com.kreait.slack.api.contract.jackson.group.auth.SuccessfulAuthTestResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.auth.AuthTestMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class SpringTestMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : AuthTestMethod() {

    override fun request(): ApiCallResult<SuccessfulAuthTestResponse, ErrorAuthTestResponse> {
        val response = SlackRequestBuilder<AuthTestResponse>(authToken, restTemplate)
                .toMethod("auth.test")
                .returnAsType(AuthTestResponse::class.java)
                .postWithJsonBody()

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
