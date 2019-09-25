package com.kreait.slack.api.spring.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorListResponse
import com.kreait.slack.api.contract.jackson.group.users.ListResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulListResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UserListMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

import com.kreait.slack.api.group.users.UsersMethodGroup


/**
 * Spring based implementation of [UsersMethodGroup.list]
 */
@Suppress("UNCHECKED_CAST")
class SpringUserListMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UserListMethod() {

    override fun request(): ApiCallResult<SuccessfulListResponse, ErrorListResponse> {
        val response = SlackRequestBuilder<ListResponse>(authToken, restTemplate)
                .toMethod("users.list")
                .returnAsType(ListResponse::class.java)
                .postUrlEncoded(this.params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulListResponse -> {
                val successfulListResponse = response.body as SuccessfulListResponse
                this.onSuccess?.invoke(successfulListResponse)
                ApiCallResult(success = successfulListResponse)
            }
            is ErrorListResponse -> {
                val errorListResponse = response.body as ErrorListResponse
                this.onFailure?.invoke(errorListResponse)
                ApiCallResult(failure = errorListResponse)
            }
        }
    }
}
