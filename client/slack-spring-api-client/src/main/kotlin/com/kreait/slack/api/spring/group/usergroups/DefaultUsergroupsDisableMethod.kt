package com.kreait.slack.api.spring.group.usergroups

import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorUsergroupsDisableResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulUsergroupsDisableResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.UsergroupsDisableResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.usergroups.UsergroupsDisableMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * https://api.slack.com/methods/usergroups.disable
 */
class DefaultUsergroupsDisableMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UsergroupsDisableMethod() {

    override fun request(): ApiCallResult<SuccessfulUsergroupsDisableResponse, ErrorUsergroupsDisableResponse> {
        val response = SlackRequestBuilder<UsergroupsDisableResponse>(authToken, restTemplate)
                .toMethod("usergroups.disable")
                .returnAsType(UsergroupsDisableResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulUsergroupsDisableResponse -> {
                val responseEntity = response.body as SuccessfulUsergroupsDisableResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorUsergroupsDisableResponse -> {
                val responseEntity = response.body as ErrorUsergroupsDisableResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
