package com.kreait.slack.api.spring.group.usergroups

import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorUpdateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulUpdateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.UpdateResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.usergroups.UsergroupsUpdateMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * https://api.slack.com/methods/usergroups.update
 */
@Suppress("UNCHECKED_CAST")
class DefaultUsergroupsUpdateMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UsergroupsUpdateMethod() {

    override fun request(): ApiCallResult<SuccessfulUpdateResponse, ErrorUpdateResponse> {
        val response = SlackRequestBuilder<UpdateResponse>(authToken, restTemplate)
                .toMethod("usergroups.update")
                .returnAsType(UpdateResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulUpdateResponse -> {
                val responseEntity = response.body as SuccessfulUpdateResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorUpdateResponse -> {
                val responseEntity = response.body as ErrorUpdateResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
