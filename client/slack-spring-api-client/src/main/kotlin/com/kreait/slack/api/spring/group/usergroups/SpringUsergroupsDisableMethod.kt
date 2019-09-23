package com.kreait.slack.api.spring.group.usergroups

import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorDisableResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulDisableResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.DisableResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.usergroups.UsergroupsDisableMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * https://api.slack.com/methods/usergroups.disable
 */
class SpringUsergroupsDisableMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UsergroupsDisableMethod() {

    override fun request(): ApiCallResult<SuccessfulDisableResponse, ErrorDisableResponse> {
        val response = SlackRequestBuilder<DisableResponse>(authToken, restTemplate)
                .toMethod("usergroups.disable")
                .returnAsType(DisableResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulDisableResponse -> {
                val responseEntity = response.body as SuccessfulDisableResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorDisableResponse -> {
                val responseEntity = response.body as ErrorDisableResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
