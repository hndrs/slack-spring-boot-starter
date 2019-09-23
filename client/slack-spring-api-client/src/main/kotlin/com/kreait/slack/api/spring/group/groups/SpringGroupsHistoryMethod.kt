package com.kreait.slack.api.spring.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsHistoryResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsHistoryResponse
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsHistoryResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.groups.GroupsHistoryMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

class SpringGroupsHistoryMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : GroupsHistoryMethod() {

    override fun request(): ApiCallResult<SuccessfulGroupsHistoryResponse, ErrorGroupsHistoryResponse> {
        val response = SlackRequestBuilder<GroupsHistoryResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("groups.history")
                .returnAsType(GroupsHistoryResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulGroupsHistoryResponse -> {
                val responseEntity = response.body as SuccessfulGroupsHistoryResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorGroupsHistoryResponse -> {
                val responseEntity = response.body as ErrorGroupsHistoryResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
