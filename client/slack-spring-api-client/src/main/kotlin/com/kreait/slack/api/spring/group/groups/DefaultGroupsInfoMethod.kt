package com.kreait.slack.api.spring.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsInfoResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsInfoResponse
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsInfoResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.groups.GroupsInfoMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

class DefaultGroupsInfoMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : GroupsInfoMethod() {

    override fun request(): ApiCallResult<SuccessfulGroupsInfoResponse, ErrorGroupsInfoResponse> {
        val response = SlackRequestBuilder<GroupsInfoResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("groups.info")
                .returnAsType(GroupsInfoResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulGroupsInfoResponse -> {
                val responseEntity = response.body as SuccessfulGroupsInfoResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorGroupsInfoResponse -> {
                val responseEntity = response.body as ErrorGroupsInfoResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
