package com.kreait.slack.api.spring.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsListResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsListResponse
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsListResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.groups.GroupsListMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


@Deprecated("Don't use this legacy method", replaceWith = ReplaceWith("conversations.list"))
class SpringGroupsListMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : GroupsListMethod() {

    override fun request(): ApiCallResult<SuccessfulGroupsListResponse, ErrorGroupsListResponse> {
        val response = SlackRequestBuilder<GroupsListResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("groups.list")
                .returnAsType(GroupsListResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulGroupsListResponse -> {
                val responseEntity = response.body as SuccessfulGroupsListResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorGroupsListResponse -> {
                val responseEntity = response.body as ErrorGroupsListResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
