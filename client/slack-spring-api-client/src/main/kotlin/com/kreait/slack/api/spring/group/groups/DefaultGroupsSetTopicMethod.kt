package com.kreait.slack.api.spring.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsSetTopicResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsSetTopicResponse
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsSetTopicResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.groups.GroupsSetTopicMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

class DefaultGroupsSetTopicMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : GroupsSetTopicMethod() {

    override fun request(): ApiCallResult<SuccessfulGroupsSetTopicResponse, ErrorGroupsSetTopicResponse> {
        val response = SlackRequestBuilder<GroupsSetTopicResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("groups.setTopic")
                .returnAsType(GroupsSetTopicResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulGroupsSetTopicResponse -> {
                val responseEntity = response.body as SuccessfulGroupsSetTopicResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorGroupsSetTopicResponse -> {
                val responseEntity = response.body as ErrorGroupsSetTopicResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
