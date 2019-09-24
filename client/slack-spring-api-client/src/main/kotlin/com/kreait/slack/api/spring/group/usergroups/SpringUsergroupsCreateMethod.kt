package com.kreait.slack.api.spring.group.usergroups

import com.kreait.slack.api.contract.jackson.group.usergroups.CreateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorCreateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulCreateResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.usergroups.UsergroupsCreateMethod
import com.kreait.slack.api.group.usergroups.UsergroupsMethodGroup
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [UsergroupsMethodGroup.create]
 */
import com.kreait.slack.api.group.users.UsersMethodGroup


/**
 * Spring based implementation of [UsersMethodGroup.conversations]
 */
@Suppress("UNCHECKED_CAST")
class SpringUsergroupsCreateMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UsergroupsCreateMethod() {

    override fun request(): ApiCallResult<SuccessfulCreateResponse, ErrorCreateResponse> {
        val response = SlackRequestBuilder<CreateResponse>(authToken, restTemplate)
                .toMethod("usergroups.create")
                .returnAsType(CreateResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {

            is SuccessfulCreateResponse -> {

                val responseEntity = response.body as SuccessfulCreateResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }

            is ErrorCreateResponse -> {

                val responseEntity = response.body as ErrorCreateResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
