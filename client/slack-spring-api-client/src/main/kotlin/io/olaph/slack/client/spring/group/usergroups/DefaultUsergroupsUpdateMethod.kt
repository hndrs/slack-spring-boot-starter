package io.olaph.slack.client.spring.group.usergroups

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.usergroups.UsergroupsUpdateMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.usergroups.ErrorUsergroupsUpdateResponse
import io.olaph.slack.dto.jackson.group.usergroups.SuccessfulUsergroupsUpdateResponse
import io.olaph.slack.dto.jackson.group.usergroups.UsergroupsUpdateResponse
import org.springframework.web.client.RestTemplate

/**
 * https://api.slack.com/methods/usergroups.update
 */
@Suppress("UNCHECKED_CAST")
class DefaultUsergroupsUpdateMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UsergroupsUpdateMethod() {

    override fun request(): ApiCallResult<SuccessfulUsergroupsUpdateResponse, ErrorUsergroupsUpdateResponse> {

        val response = SlackRequestBuilder<UsergroupsUpdateResponse>(authToken, restTemplate)
                .toMethod("usergroups.update")
                .returnAsType(UsergroupsUpdateResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {

            is SuccessfulUsergroupsUpdateResponse -> {

                val responseEntity = response.body as SuccessfulUsergroupsUpdateResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }

            is ErrorUsergroupsUpdateResponse -> {

                val responseEntity = response.body as ErrorUsergroupsUpdateResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}