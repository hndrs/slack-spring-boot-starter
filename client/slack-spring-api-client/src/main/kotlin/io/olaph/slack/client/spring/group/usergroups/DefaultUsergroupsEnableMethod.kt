package io.olaph.slack.client.spring.group.usergroups

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.usergroups.UsergroupsEnableMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.usergroups.ErrorUsergroupsEnableResponse
import io.olaph.slack.dto.jackson.group.usergroups.SuccessfulUsergroupsEnableResponse
import io.olaph.slack.dto.jackson.group.usergroups.UsergroupsEnableResponse
import org.springframework.web.client.RestTemplate

/**
 * https://api.slack.com/methods/usergroups.enable
 */
class DefaultUsergroupsEnableMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UsergroupsEnableMethod() {

    override fun request(): ApiCallResult<SuccessfulUsergroupsEnableResponse, ErrorUsergroupsEnableResponse> {

        val response = SlackRequestBuilder<UsergroupsEnableResponse>(authToken, restTemplate)
                .toMethod("usergroups.enable")
                .returnAsType(UsergroupsEnableResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {

            is SuccessfulUsergroupsEnableResponse -> {

                val responseEntity = response.body as SuccessfulUsergroupsEnableResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }

            is ErrorUsergroupsEnableResponse -> {

                val responseEntity = response.body as ErrorUsergroupsEnableResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}