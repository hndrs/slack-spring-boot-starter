package com.kreait.slack.api.spring.group.usergroups.users

import com.kreait.slack.api.contract.jackson.group.usergroups.users.ErrorUsergroupUsersUpdateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.users.SuccessfulUsergroupUsersUpdateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.users.UsergroupsUsersUpdateResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.usergroups.users.UsergroupsUsersUpdateMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * https://api.slack.com/methods/usergroups.users.update
 */
@Suppress("UNCHECKED_CAST")
class DefaultUsergroupsUsersUpdateMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UsergroupsUsersUpdateMethod() {

    override fun request(): ApiCallResult<SuccessfulUsergroupUsersUpdateResponse, ErrorUsergroupUsersUpdateResponse> {
        val response = SlackRequestBuilder<UsergroupsUsersUpdateResponse>(authToken, restTemplate)
                .toMethod("usergroups.users.update")
                .returnAsType(UsergroupsUsersUpdateResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulUsergroupUsersUpdateResponse -> {
                val responseEntity = response.body as SuccessfulUsergroupUsersUpdateResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorUsergroupUsersUpdateResponse -> {
                val responseEntity = response.body as ErrorUsergroupUsersUpdateResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
