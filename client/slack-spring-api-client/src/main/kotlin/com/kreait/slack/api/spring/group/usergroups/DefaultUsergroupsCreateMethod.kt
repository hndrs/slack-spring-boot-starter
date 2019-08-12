package com.kreait.slack.api.spring.group.usergroups

import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.usergroups.UsergroupsCreateMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorUsergroupsCreateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulUsergroupsCreateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.UsergroupsCreateResponse
import org.springframework.web.client.RestTemplate

/**
 * https://api.slack.com/methods/usergroups.create
 */
@Suppress("UNCHECKED_CAST")
class DefaultUsergroupsCreateMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UsergroupsCreateMethod() {

    override fun request(): ApiCallResult<SuccessfulUsergroupsCreateResponse, ErrorUsergroupsCreateResponse> {
        val response = SlackRequestBuilder<UsergroupsCreateResponse>(authToken, restTemplate)
                .toMethod("usergroups.create")
                .returnAsType(UsergroupsCreateResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {

            is SuccessfulUsergroupsCreateResponse -> {

                val responseEntity = response.body as SuccessfulUsergroupsCreateResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }

            is ErrorUsergroupsCreateResponse -> {

                val responseEntity = response.body as ErrorUsergroupsCreateResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
