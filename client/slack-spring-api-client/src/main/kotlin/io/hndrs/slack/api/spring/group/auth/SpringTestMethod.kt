package io.hndrs.slack.api.spring.group.auth


import io.hndrs.slack.api.contract.jackson.group.auth.AuthTestResponse
import io.hndrs.slack.api.contract.jackson.group.auth.ErrorAuthTestResponse
import io.hndrs.slack.api.contract.jackson.group.auth.SuccessfulAuthTestResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.auth.AuthTestMethod
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Checks authentication & identity.
 * https://api.slack.com/methods/auth.test
 */
@Suppress("UNCHECKED_CAST")
class SpringTestMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate()
) : io.hndrs.slack.api.group.auth.AuthTestMethod() {

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulAuthTestResponse, ErrorAuthTestResponse> {
        val response = SlackRequestBuilder<AuthTestResponse>(authToken, restTemplate)
            .toMethod("auth.test")
            .returnAsType(AuthTestResponse::class.java)
            .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulAuthTestResponse -> {
                val responseEntity = response.body as SuccessfulAuthTestResponse
                this.onSuccess?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(success = responseEntity)
            }
            is ErrorAuthTestResponse -> {
                val responseEntity = response.body as ErrorAuthTestResponse
                this.onFailure?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(failure = responseEntity)
            }
        }
    }
}
