package io.olaph.slack.client.spring.group.users


import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.users.UsersGetProfileMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.users.ErrorUsersGetProfileResponse
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersGetProfileResponse
import io.olaph.slack.dto.jackson.group.users.UsersGetProfileResponse
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultUsersGetProfileMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UsersGetProfileMethod() {

    override fun request(): ApiCallResult<SuccessfulUsersGetProfileResponse, ErrorUsersGetProfileResponse> {
        val response = SlackRequestBuilder<UsersGetProfileResponse>(authToken, restTemplate)
                .toMethod("users.profile.get")
                .returnAsType(UsersGetProfileResponse::class.java)
                .postUrlEncoded(this.params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulUsersGetProfileResponse -> {
                val responseEntity = response.body as SuccessfulUsersGetProfileResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorUsersGetProfileResponse -> {
                val responseEntity = response.body as ErrorUsersGetProfileResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
