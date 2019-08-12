package com.kreait.slack.api.spring.group.usergroups

import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorUsergroupsEnableResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulUsergroupsEnableResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.UsergroupsEnableResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.usergroups.UsergroupsEnableMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * https://api.slack.com/methods/usergroups.enable
 */
class DefaultUsergroupsEnableMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UsergroupsEnableMethod() {

    override fun request(): ApiCallResult<SuccessfulUsergroupsEnableResponse, ErrorUsergroupsEnableResponse> {
        val response = SlackRequestBuilder<UsergroupsEnableResponse>(authToken, restTemplate)
                .toMethod("usergroups.enable")
                .returnAsType(UsergroupsEnableResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulUsergroupsEnableResponse -> {
                val responseEntity = response.body as SuccessfulUsergroupsEnableResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorUsergroupsEnableResponse -> {
                val responseEntity = response.body as ErrorUsergroupsEnableResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
