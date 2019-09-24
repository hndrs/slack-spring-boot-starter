package com.kreait.slack.api.spring.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsOpenResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsOpenResponse
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsOpenResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.groups.GroupsOpenMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

import com.kreait.slack.api.group.groups.GroupsMethodGroup

/**
 * Spring based implementation of [GroupsMethodGroup.open]
 */
class SpringGroupsOpenMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : GroupsOpenMethod() {

    override fun request(): ApiCallResult<SuccessfulGroupsOpenResponse, ErrorGroupsOpenResponse> {
        val response = SlackRequestBuilder<GroupsOpenResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("groups.open")
                .returnAsType(GroupsOpenResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulGroupsOpenResponse -> {
                val responseEntity = response.body as SuccessfulGroupsOpenResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorGroupsOpenResponse -> {
                val responseEntity = response.body as ErrorGroupsOpenResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
