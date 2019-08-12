package com.kreait.slack.api.spring.group.usergroups.users

import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.usergroups.users.UsergroupsUsersListMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import com.kreait.slack.api.contract.jackson.group.usergroups.users.ErrorUsergroupsUsersListResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.users.SuccessfulUsergroupsUsersListResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.users.UsergroupsUsersListResponse
import org.springframework.web.client.RestTemplate

class DefaultUsergroupsUsersListMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UsergroupsUsersListMethod() {

    override fun request(): ApiCallResult<SuccessfulUsergroupsUsersListResponse, ErrorUsergroupsUsersListResponse> {
        val response = SlackRequestBuilder<UsergroupsUsersListResponse>(authToken, restTemplate)
                .toMethod("usergroups.users.list")
                .returnAsType(UsergroupsUsersListResponse::class.java)
                .postUrlEncoded(this.params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulUsergroupsUsersListResponse -> {
                val responseEntity = response.body as SuccessfulUsergroupsUsersListResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorUsergroupsUsersListResponse -> {
                val responseEntity = response.body as ErrorUsergroupsUsersListResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
