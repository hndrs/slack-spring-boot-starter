package io.hndrs.slack.api.spring.group.team


import io.hndrs.slack.api.contract.jackson.group.team.ErrorProfileResponse
import io.hndrs.slack.api.contract.jackson.group.team.ProfileResponse
import io.hndrs.slack.api.contract.jackson.group.team.SuccessfulProfileResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.team.TeamGetProfileMethod
import io.hndrs.slack.api.group.team.TeamMethodGroup
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * Spring based implementation of [TeamMethodGroup.getProfile]
 */
@Suppress("UNCHECKED_CAST")
class SpringTeamGetProfileMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()
) : TeamGetProfileMethod() {

    override fun request(): ApiCallResult<SuccessfulProfileResponse, ErrorProfileResponse> {
        val response = SlackRequestBuilder<ProfileResponse>(authToken, restTemplate)
            .toMethod("team.profile.get")
            .returnAsType(ProfileResponse::class.java)
            .postUrlEncoded(this.params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulProfileResponse -> {
                val responseEntity = response.body as SuccessfulProfileResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorProfileResponse -> {
                val responseEntity = response.body as ErrorProfileResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
