package com.kreait.slack.api.spring.group.team


import com.kreait.slack.api.contract.jackson.group.team.ErrorTeamGetProfileResponse
import com.kreait.slack.api.contract.jackson.group.team.SuccessfulTeamGetProfileResponse
import com.kreait.slack.api.contract.jackson.group.team.TeamGetProfileResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.team.TeamGetProfileMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultTeamGetProfileMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : TeamGetProfileMethod() {

    override fun request(): ApiCallResult<SuccessfulTeamGetProfileResponse, ErrorTeamGetProfileResponse> {
        val response = SlackRequestBuilder<TeamGetProfileResponse>(authToken, restTemplate)
                .toMethod("team.profile.get")
                .returnAsType(TeamGetProfileResponse::class.java)
                .postUrlEncoded(this.params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulTeamGetProfileResponse -> {
                val responseEntity = response.body as SuccessfulTeamGetProfileResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorTeamGetProfileResponse -> {
                val responseEntity = response.body as ErrorTeamGetProfileResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
