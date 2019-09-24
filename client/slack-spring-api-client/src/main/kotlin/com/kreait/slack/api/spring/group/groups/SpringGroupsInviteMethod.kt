package com.kreait.slack.api.spring.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsInviteResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsInviteResponse
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsInviteResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.groups.GroupsInviteMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

import com.kreait.slack.api.group.groups.GroupsMethodGroup

/**
 * Spring based implementation of [GroupsMethodGroup.invite]
 */
class SpringGroupsInviteMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : GroupsInviteMethod() {

    override fun request(): ApiCallResult<SuccessfulGroupsInviteResponse, ErrorGroupsInviteResponse> {
        val response = SlackRequestBuilder<GroupsInviteResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("groups.invite")
                .returnAsType(GroupsInviteResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulGroupsInviteResponse -> {
                val responseEntity = response.body as SuccessfulGroupsInviteResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorGroupsInviteResponse -> {
                val responseEntity = response.body as ErrorGroupsInviteResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
