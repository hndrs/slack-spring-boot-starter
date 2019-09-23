package com.kreait.slack.api.spring.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsCreateResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsCreateResponse
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsCreateResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.groups.GroupsCreateMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

class SpringGroupsCreateMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : GroupsCreateMethod() {

    override fun request(): ApiCallResult<SuccessfulGroupsCreateResponse, ErrorGroupsCreateResponse> {
        val response = SlackRequestBuilder<GroupsCreateResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("groups.create")
                .returnAsType(GroupsCreateResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulGroupsCreateResponse -> {
                val responseEntity = response.body as SuccessfulGroupsCreateResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorGroupsCreateResponse -> {
                val responseEntity = response.body as ErrorGroupsCreateResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
