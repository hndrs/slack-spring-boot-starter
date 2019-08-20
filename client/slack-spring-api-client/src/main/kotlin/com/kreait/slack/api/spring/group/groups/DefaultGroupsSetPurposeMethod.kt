package com.kreait.slack.api.spring.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsSetPurposeResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsSetPurposeResponse
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsSetPurposeResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.groups.GroupsSetPurposeMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

class DefaultGroupsSetPurposeMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : GroupsSetPurposeMethod() {

    override fun request(): ApiCallResult<SuccessfulGroupsSetPurposeResponse, ErrorGroupsSetPurposeResponse> {
        val response = SlackRequestBuilder<GroupsSetPurposeResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("groups.setPurpose")
                .returnAsType(GroupsSetPurposeResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulGroupsSetPurposeResponse -> {
                val responseEntity = response.body as SuccessfulGroupsSetPurposeResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorGroupsSetPurposeResponse -> {
                val responseEntity = response.body as ErrorGroupsSetPurposeResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
