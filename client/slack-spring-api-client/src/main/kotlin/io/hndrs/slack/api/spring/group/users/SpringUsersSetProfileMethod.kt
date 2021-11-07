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
    private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()
) : UsersSetProfileMethod() {

    override fun request(): ApiCallResult<SuccessfulSetProfileResponse, ErrorSetProfileResponse> {
        val response = SlackRequestBuilder<SetProfileResponse>(authToken, restTemplate)
            .toMethod("users.profile.set")
            .returnAsType(SetProfileResponse::class.java)
            .postUrlEncoded(mapOf())

        return when (response.body!!) {
            is SuccessfulSetProfileResponse -> {
                val responseEntity = response.body as SuccessfulSetProfileResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorSetProfileResponse -> {
                val responseEntity = response.body as ErrorSetProfileResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
