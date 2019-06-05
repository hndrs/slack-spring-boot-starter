package io.olaph.slack.client.spring.group.usergroups.users

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.usergroups.users.UsergroupsUsersUpdateMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.usergroups.users.ErrorUsergroupUsersUpdateResponse
import io.olaph.slack.dto.jackson.group.usergroups.users.SuccessfulUsergroupUsersUpdateResponse
import io.olaph.slack.dto.jackson.group.usergroups.users.UsergroupsUsersUpdateResponse
import org.springframework.web.client.RestTemplate

/**
 * https://api.slack.com/methods/usergroups.users.update
 */
@Suppress("UNCHECKED_CAST")
class DefaultUsergroupsUsersUpdateMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UsergroupsUsersUpdateMethod() {

    override fun request(): ApiCallResult<SuccessfulUsergroupUsersUpdateResponse, ErrorUsergroupUsersUpdateResponse> {
        val response = SlackRequestBuilder<UsergroupsUsersUpdateResponse>(authToken, restTemplate)
                .toMethod("usergroups.users.update")
                .returnAsType(UsergroupsUsersUpdateResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulUsergroupUsersUpdateResponse -> {
                val responseEntity = response.body as SuccessfulUsergroupUsersUpdateResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorUsergroupUsersUpdateResponse -> {
                val responseEntity = response.body as ErrorUsergroupUsersUpdateResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
