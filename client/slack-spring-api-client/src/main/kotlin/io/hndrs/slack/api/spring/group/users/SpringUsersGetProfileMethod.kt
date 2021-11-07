package io.hndrs.slack.api.spring.group.users


import io.hndrs.slack.api.contract.jackson.group.users.ErrorGetProfileResponse
import io.hndrs.slack.api.contract.jackson.group.users.GetProfileResponse
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulGetProfileResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.users.UsersGetProfileMethod
import io.hndrs.slack.api.group.users.UsersMethodGroup
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * Spring based implementation of [UsersMethodGroup.getProfile]
 */
@Suppress("UNCHECKED_CAST")
class SpringUsersGetProfileMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()
) : UsersGetProfileMethod() {

    override fun request(): ApiCallResult<SuccessfulGetProfileResponse, ErrorGetProfileResponse> {
        val response = SlackRequestBuilder<GetProfileResponse>(authToken, restTemplate)
            .toMethod("users.profile.get")
            .returnAsType(GetProfileResponse::class.java)
            .postUrlEncoded(this.params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulGetProfileResponse -> {
                val responseEntity = response.body as SuccessfulGetProfileResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorGetProfileResponse -> {
                val responseEntity = response.body as ErrorGetProfileResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
