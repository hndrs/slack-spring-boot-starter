package io.olaph.slack.client.spring.group.team


import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.team.TeamGetProfileMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.team.ErrorTeamGetProfileResponse
import io.olaph.slack.dto.jackson.group.team.SuccessfulTeamGetProfileResponse
import io.olaph.slack.dto.jackson.group.team.TeamGetProfileResponse
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
