package io.hndrs.slack.api.spring.group.auth


import io.hndrs.slack.api.contract.jackson.group.auth.AuthRevokeResponse
import io.hndrs.slack.api.contract.jackson.group.auth.ErrorAuthRevokeResponse
import io.hndrs.slack.api.contract.jackson.group.auth.SuccessfulAuthRevokeResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.auth.AuthRevokeMethod
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Revokes a token.
 * https://api.slack.com/methods/auth.revoke
 */
class SpringRevokeMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate()
) : io.hndrs.slack.api.group.auth.AuthRevokeMethod() {

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulAuthRevokeResponse, ErrorAuthRevokeResponse> {

        val response = SlackRequestBuilder<AuthRevokeResponse>(authToken, restTemplate)
            .toMethod("auth.revoke")
            .returnAsType(AuthRevokeResponse::class.java)
            .postUrlEncoded(this.params.toRequestMap())
        return when (response.body!!) {
            is SuccessfulAuthRevokeResponse -> {
                val responseEntity = response.body as SuccessfulAuthRevokeResponse
                this.onSuccess?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(success = responseEntity)
            }
            is ErrorAuthRevokeResponse -> {
                val responseEntity = response.body as ErrorAuthRevokeResponse
                this.onFailure?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(failure = responseEntity)
            }
        }
    }
}
