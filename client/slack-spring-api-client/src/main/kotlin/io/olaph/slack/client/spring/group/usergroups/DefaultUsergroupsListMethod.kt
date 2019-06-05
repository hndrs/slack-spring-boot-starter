package io.olaph.slack.client.spring.group.usergroups

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.usergroups.UsergroupsListMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.usergroups.ErrorUsergroupsListResponse
import io.olaph.slack.dto.jackson.group.usergroups.SuccessfulUsergroupsListResponse
import io.olaph.slack.dto.jackson.group.usergroups.UsergroupsListResponse
import org.springframework.web.client.RestTemplate

/**
 * https://api.slack.com/methods/usergroups.list
 */
@Suppress("UNCHECKED_CAST")
class DefaultUsergroupsListMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UsergroupsListMethod() {

    override fun request(): ApiCallResult<SuccessfulUsergroupsListResponse, ErrorUsergroupsListResponse> {
        val response = SlackRequestBuilder<UsergroupsListResponse>(authToken, restTemplate)
                .toMethod("usergroups.list")
                .returnAsType(UsergroupsListResponse::class.java)
                .postUrlEncoded(this.params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulUsergroupsListResponse -> {
                val responseEntity = response.body as SuccessfulUsergroupsListResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorUsergroupsListResponse -> {
                val responseEntity = response.body as ErrorUsergroupsListResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}