package com.kreait.slack.api.spring.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsKickResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsKickResponse
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsKickResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.groups.GroupsKickMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

import com.kreait.slack.api.group.groups.GroupsMethodGroup

/**
 * Spring based implementation of [GroupsMethodGroup.kick]
 */
class SpringGroupsKickMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : GroupsKickMethod() {

    override fun request(): ApiCallResult<SuccessfulGroupsKickResponse, ErrorGroupsKickResponse> {
        val response = SlackRequestBuilder<GroupsKickResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("groups.kick")
                .returnAsType(GroupsKickResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulGroupsKickResponse -> {
                val responseEntity = response.body as SuccessfulGroupsKickResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorGroupsKickResponse -> {
                val responseEntity = response.body as ErrorGroupsKickResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
