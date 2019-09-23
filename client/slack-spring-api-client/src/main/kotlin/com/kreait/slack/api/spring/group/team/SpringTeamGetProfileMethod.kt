package com.kreait.slack.api.spring.group.team


import com.kreait.slack.api.contract.jackson.group.team.ErrorProfileResponse
import com.kreait.slack.api.contract.jackson.group.team.SuccessfulProfileResponse
import com.kreait.slack.api.contract.jackson.group.team.ProfileResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.team.TeamGetProfileMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class SpringTeamGetProfileMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : TeamGetProfileMethod() {

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
