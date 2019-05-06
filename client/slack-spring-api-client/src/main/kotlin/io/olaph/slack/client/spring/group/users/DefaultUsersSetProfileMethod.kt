package io.olaph.slack.client.spring.group.users


import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.users.UsersSetProfileMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.users.ErrorUsersSetProfileResponse
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersSetProfileResponse
import io.olaph.slack.dto.jackson.group.users.UsersSetProfileResponse
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultUsersSetProfileMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UsersSetProfileMethod() {

    override fun request(): ApiCallResult<SuccessfulUsersSetProfileResponse, ErrorUsersSetProfileResponse> {
        val response = SlackRequestBuilder<UsersSetProfileResponse>(authToken, restTemplate)
                .toMethod("users.profile.set")
                .returnAsType(UsersSetProfileResponse::class.java)
                .postUrlEncoded(mapOf())

        return when (response.body!!) {
            is SuccessfulUsersSetProfileResponse -> {
                val responseEntity = response.body as SuccessfulUsersSetProfileResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorUsersSetProfileResponse -> {
                val responseEntity = response.body as ErrorUsersSetProfileResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
