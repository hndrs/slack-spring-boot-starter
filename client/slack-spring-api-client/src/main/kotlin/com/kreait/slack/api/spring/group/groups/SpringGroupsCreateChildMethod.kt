package com.kreait.slack.api.spring.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsCreateChildResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsCreateChildResponse
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsCreateChildResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.groups.GroupsCreateChildMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

class SpringGroupsCreateChildMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : GroupsCreateChildMethod() {

    override fun request(): ApiCallResult<SuccessfulGroupsCreateChildResponse, ErrorGroupsCreateChildResponse> {
        val response = SlackRequestBuilder<GroupsCreateChildResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("groups.createChild")
                .returnAsType(GroupsCreateChildResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulGroupsCreateChildResponse -> {
                val responseEntity = response.body as SuccessfulGroupsCreateChildResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorGroupsCreateChildResponse -> {
                val responseEntity = response.body as ErrorGroupsCreateChildResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
