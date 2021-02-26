package io.hndrs.slack.api.spring.group.users


import io.hndrs.slack.api.contract.jackson.group.users.ErrorSetProfileResponse
import io.hndrs.slack.api.contract.jackson.group.users.SetProfileResponse
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulSetProfileResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.users.UsersMethodGroup
import io.hndrs.slack.api.group.users.UsersSetProfileMethod
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * Spring based implementation of [UsersMethodGroup.setProfile]
 */
@Suppress("UNCHECKED_CAST")
class SpringUsersSetProfileMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate()
) : io.hndrs.slack.api.group.users.UsersSetProfileMethod() {

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulSetProfileResponse, ErrorSetProfileResponse> {
        val response = SlackRequestBuilder<SetProfileResponse>(authToken, restTemplate)
            .toMethod("users.profile.set")
            .returnAsType(SetProfileResponse::class.java)
            .postUrlEncoded(mapOf())

        return when (response.body!!) {
            is SuccessfulSetProfileResponse -> {
                val responseEntity = response.body as SuccessfulSetProfileResponse
                this.onSuccess?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(success = responseEntity)
            }
            is ErrorSetProfileResponse -> {
                val responseEntity = response.body as ErrorSetProfileResponse
                this.onFailure?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(failure = responseEntity)
            }
        }
    }
}
