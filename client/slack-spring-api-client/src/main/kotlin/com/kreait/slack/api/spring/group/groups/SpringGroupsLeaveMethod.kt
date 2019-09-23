package com.kreait.slack.api.spring.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsLeaveResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsLeaveResponse
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsLeaveResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.groups.GroupsLeaveMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

class SpringGroupsLeaveMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : GroupsLeaveMethod() {

    override fun request(): ApiCallResult<SuccessfulGroupsLeaveResponse, ErrorGroupsLeaveResponse> {
        val response = SlackRequestBuilder<GroupsLeaveResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("groups.leave")
                .returnAsType(GroupsLeaveResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulGroupsLeaveResponse -> {
                val responseEntity = response.body as SuccessfulGroupsLeaveResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorGroupsLeaveResponse -> {
                val responseEntity = response.body as ErrorGroupsLeaveResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
