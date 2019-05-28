package io.olaph.slack.client.spring.group.usergroups.users

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.usergroups.users.UsergroupsUsersListMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.usergroups.users.ErrorUsergroupsUsersListResponse
import io.olaph.slack.dto.jackson.group.usergroups.users.SlackUsergroupsUsersListRequest
import io.olaph.slack.dto.jackson.group.usergroups.users.SuccessfulUsergroupsUsersListResponse
import io.olaph.slack.dto.jackson.group.usergroups.users.UsergroupsUsersListResponse
import org.springframework.web.client.RestTemplate

class DefaultUsergroupsUsersListMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UsergroupsUsersListMethod() {

    override fun request(): ApiCallResult<SuccessfulUsergroupsUsersListResponse, ErrorUsergroupsUsersListResponse> {

        val response = SlackRequestBuilder<UsergroupsUsersListResponse>(authToken, restTemplate)
                .toMethod("usergroups.users.list")
                .returnAsType(UsergroupsUsersListResponse::class.java)
                .postUrlEncoded(this.params.toRequestMap())

        return when (response.body!!) {

            is SuccessfulUsergroupsUsersListResponse -> {

                val responseEntity = response.body as SuccessfulUsergroupsUsersListResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }

            is ErrorUsergroupsUsersListResponse -> {

                val responseEntity = response.body as ErrorUsergroupsUsersListResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}