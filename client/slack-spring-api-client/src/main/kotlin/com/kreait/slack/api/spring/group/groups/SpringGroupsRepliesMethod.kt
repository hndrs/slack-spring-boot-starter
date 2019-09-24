package com.kreait.slack.api.spring.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsRepliesResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsRepliesResponse
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsRepliesResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.groups.GroupsRepliesMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

import com.kreait.slack.api.group.groups.GroupsMethodGroup

/**
 * Spring based implementation of [GroupsMethodGroup.replies]
 */
class SpringGroupsRepliesMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : GroupsRepliesMethod() {

    override fun request(): ApiCallResult<SuccessfulGroupsRepliesResponse, ErrorGroupsRepliesResponse> {
        val response = SlackRequestBuilder<GroupsRepliesResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("groups.replies")
                .returnAsType(GroupsRepliesResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulGroupsRepliesResponse -> {
                val responseEntity = response.body as SuccessfulGroupsRepliesResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorGroupsRepliesResponse -> {
                val responseEntity = response.body as ErrorGroupsRepliesResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
