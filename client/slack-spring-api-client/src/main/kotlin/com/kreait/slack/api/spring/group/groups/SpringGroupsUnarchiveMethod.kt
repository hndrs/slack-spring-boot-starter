package com.kreait.slack.api.spring.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsUnarchiveResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsUnarchiveResponse
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsUnarchiveResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.groups.GroupsUnarchiveMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

class SpringGroupsUnarchiveMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : GroupsUnarchiveMethod() {

    override fun request(): ApiCallResult<SuccessfulGroupsUnarchiveResponse, ErrorGroupsUnarchiveResponse> {
        val response = SlackRequestBuilder<GroupsUnarchiveResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("groups.unarchive")
                .returnAsType(GroupsUnarchiveResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulGroupsUnarchiveResponse -> {
                val responseEntity = response.body as SuccessfulGroupsUnarchiveResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorGroupsUnarchiveResponse -> {
                val responseEntity = response.body as ErrorGroupsUnarchiveResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
