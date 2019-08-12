package com.kreait.slack.api.spring.group.usergroups

import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorUsergroupsUpdateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulUsergroupsUpdateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.UsergroupsUpdateResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.usergroups.UsergroupsUpdateMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * https://api.slack.com/methods/usergroups.update
 */
@Suppress("UNCHECKED_CAST")
class DefaultUsergroupsUpdateMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UsergroupsUpdateMethod() {

    override fun request(): ApiCallResult<SuccessfulUsergroupsUpdateResponse, ErrorUsergroupsUpdateResponse> {
        val response = SlackRequestBuilder<UsergroupsUpdateResponse>(authToken, restTemplate)
                .toMethod("usergroups.update")
                .returnAsType(UsergroupsUpdateResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulUsergroupsUpdateResponse -> {
                val responseEntity = response.body as SuccessfulUsergroupsUpdateResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorUsergroupsUpdateResponse -> {
                val responseEntity = response.body as ErrorUsergroupsUpdateResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
