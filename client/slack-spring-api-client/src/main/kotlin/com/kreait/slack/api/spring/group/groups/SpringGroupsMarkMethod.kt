package com.kreait.slack.api.spring.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsMarkResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsMarkResponse
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsMarkResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.groups.GroupsMarkMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

import com.kreait.slack.api.group.groups.GroupsMethodGroup

/**
 * Spring based implementation of [GroupsMethodGroup.mark]
 */
class SpringGroupsMarkMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : GroupsMarkMethod() {

    override fun request(): ApiCallResult<SuccessfulGroupsMarkResponse, ErrorGroupsMarkResponse> {
        val response = SlackRequestBuilder<GroupsMarkResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("groups.mark")
                .returnAsType(GroupsMarkResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulGroupsMarkResponse -> {
                val responseEntity = response.body as SuccessfulGroupsMarkResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorGroupsMarkResponse -> {
                val responseEntity = response.body as ErrorGroupsMarkResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
