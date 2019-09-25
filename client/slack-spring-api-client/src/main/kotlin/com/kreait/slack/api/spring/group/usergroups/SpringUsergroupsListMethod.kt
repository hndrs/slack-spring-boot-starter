package com.kreait.slack.api.spring.group.usergroups

import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorListResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.ListResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulListResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.usergroups.UsergroupsListMethod
import com.kreait.slack.api.group.usergroups.UsergroupsMethodGroup
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [UsergroupsMethodGroup.list]
 */
import com.kreait.slack.api.group.users.UsersMethodGroup


/**
 * Spring based implementation of [UsersMethodGroup.conversations]
 */
@Suppress("UNCHECKED_CAST")
class SpringUsergroupsListMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UsergroupsListMethod() {

    override fun request(): ApiCallResult<SuccessfulListResponse, ErrorListResponse> {
        val response = SlackRequestBuilder<ListResponse>(authToken, restTemplate)
                .toMethod("usergroups.list")
                .returnAsType(ListResponse::class.java)
                .postUrlEncoded(this.params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulListResponse -> {
                val responseEntity = response.body as SuccessfulListResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorListResponse -> {
                val responseEntity = response.body as ErrorListResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
