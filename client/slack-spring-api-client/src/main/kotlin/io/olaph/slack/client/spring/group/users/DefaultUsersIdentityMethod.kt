package io.olaph.slack.client.spring.group.users


import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.users.UsersIdentityMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.users.ErrorUsersIdentityResponse
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersIdentityResponse
import io.olaph.slack.dto.jackson.group.users.UsersIdentityResponse
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultUsersIdentityMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UsersIdentityMethod() {

    override fun request(): ApiCallResult<SuccessfulUsersIdentityResponse, ErrorUsersIdentityResponse> {
        val response = SlackRequestBuilder<UsersIdentityResponse>(authToken, restTemplate)
                .toMethod("users.identity")
                .returnAsType(UsersIdentityResponse::class.java)
                .postUrlEncoded(mapOf())

        return when (response.body!!) {
            is SuccessfulUsersIdentityResponse -> {
                val responseEntity = response.body as SuccessfulUsersIdentityResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorUsersIdentityResponse -> {
                val responseEntity = response.body as ErrorUsersIdentityResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
