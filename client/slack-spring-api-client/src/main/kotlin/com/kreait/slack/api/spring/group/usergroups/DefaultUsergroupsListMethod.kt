package com.kreait.slack.api.spring.group.usergroups

import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.usergroups.UsergroupsListMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorUsergroupsListResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulUsergroupsListResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.UsergroupsListResponse
import org.springframework.web.client.RestTemplate

/**
 * https://api.slack.com/methods/usergroups.list
 */
@Suppress("UNCHECKED_CAST")
class DefaultUsergroupsListMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UsergroupsListMethod() {

    override fun request(): ApiCallResult<SuccessfulUsergroupsListResponse, ErrorUsergroupsListResponse> {
        val response = SlackRequestBuilder<UsergroupsListResponse>(authToken, restTemplate)
                .toMethod("usergroups.list")
                .returnAsType(UsergroupsListResponse::class.java)
                .postUrlEncoded(this.params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulUsergroupsListResponse -> {
                val responseEntity = response.body as SuccessfulUsergroupsListResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorUsergroupsListResponse -> {
                val responseEntity = response.body as ErrorUsergroupsListResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
