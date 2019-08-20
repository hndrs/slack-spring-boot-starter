package com.kreait.slack.api.spring.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsRenameResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsRenameResponse
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsRenameResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.groups.GroupsRenameMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

class DefaultGroupsRenameMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : GroupsRenameMethod() {

    override fun request(): ApiCallResult<SuccessfulGroupsRenameResponse, ErrorGroupsRenameResponse> {
        val response = SlackRequestBuilder<GroupsRenameResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("groups.rename")
                .returnAsType(GroupsRenameResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulGroupsRenameResponse -> {
                val responseEntity = response.body as SuccessfulGroupsRenameResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorGroupsRenameResponse -> {
                val responseEntity = response.body as ErrorGroupsRenameResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
