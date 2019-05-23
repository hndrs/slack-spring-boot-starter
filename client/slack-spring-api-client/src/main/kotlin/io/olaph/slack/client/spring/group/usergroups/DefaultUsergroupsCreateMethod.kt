package io.olaph.slack.client.spring.group.usergroups

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.usergroups.UsergroupsCreateMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.common.types.Usergroup
import io.olaph.slack.dto.jackson.group.usergroups.ErrorUsergroupsCreateResponse
import io.olaph.slack.dto.jackson.group.usergroups.SuccessfulUsergroupsCreateResponse
import io.olaph.slack.dto.jackson.group.usergroups.UsergroupsCreateResponse
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