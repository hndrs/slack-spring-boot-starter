package io.olaph.slack.client.spring.group.usergroups

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.usergroups.UsergroupsDisableMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.usergroups.ErrorUsergroupsDisableResponse
import io.olaph.slack.dto.jackson.group.usergroups.SuccessfulUsergroupsDisableResponse
import io.olaph.slack.dto.jackson.group.usergroups.UsergroupsDisableResponse
import org.springframework.web.client.RestTemplate

/**
 * https://api.slack.com/methods/usergroups.disable
 */
class DefaultUsergroupsDisableMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UsergroupsDisableMethod() {

    override fun request(): ApiCallResult<SuccessfulUsergroupsDisableResponse, ErrorUsergroupsDisableResponse> {
        val response = SlackRequestBuilder<UsergroupsDisableResponse>(authToken, restTemplate)
                .toMethod("usergroups.disable")
                .returnAsType(UsergroupsDisableResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulUsergroupsDisableResponse -> {
                val responseEntity = response.body as SuccessfulUsergroupsDisableResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorUsergroupsDisableResponse -> {
                val responseEntity = response.body as ErrorUsergroupsDisableResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}