package com.kreait.slack.api.spring.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsArchiveResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsArchiveResponse
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsArchiveResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.groups.GroupsArchiveMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate
import com.kreait.slack.api.group.groups.GroupsMethodGroup

/**
 * Spring based implementation of [GroupsMethodGroup.archive]
 */
class SpringGroupsArchiveMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : GroupsArchiveMethod() {

    override fun request(): ApiCallResult<SuccessfulGroupsArchiveResponse, ErrorGroupsArchiveResponse> {
        val response = SlackRequestBuilder<GroupsArchiveResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("groups.archive")
                .returnAsType(GroupsArchiveResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulGroupsArchiveResponse -> {
                val responseEntity = response.body as SuccessfulGroupsArchiveResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorGroupsArchiveResponse -> {
                val responseEntity = response.body as ErrorGroupsArchiveResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
