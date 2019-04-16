package io.olaph.slack.client.spring.group.channels

import io.olaph.slack.client.UnknownResponseException
import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.channels.ChannelsInviteMethod
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.channels.ErrorChannelInviteResponse
import io.olaph.slack.dto.jackson.group.channels.SlackChannelInviteResponse
import io.olaph.slack.dto.jackson.group.channels.SuccessfulChannelInviteResponse
import org.springframework.web.client.RestTemplate
import org.springframework.http.client.BufferingClientHttpRequestFactory
import org.springframework.http.client.SimpleClientHttpRequestFactory


@Suppress("UNCHECKED_CAST")
class DefaultChannelInviteMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplate(BufferingClientHttpRequestFactory(SimpleClientHttpRequestFactory()))) : ChannelsInviteMethod() {

    override fun request(): ApiCallResult<SuccessfulChannelInviteResponse, ErrorChannelInviteResponse> {
        val response = SlackRequestBuilder<SlackChannelInviteResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("channels.invite")
                .returnAsType(SlackChannelInviteResponse::class.java)
                .postWithJsonBody()

        return when {
            response.body is SuccessfulChannelInviteResponse -> {
                val responseEntity = response.body as SuccessfulChannelInviteResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            response.body is ErrorChannelInviteResponse -> {
                val responseEntity = response.body as ErrorChannelInviteResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
            else -> {
                throw UnknownResponseException(this::class, response)
            }

        }
    }
}
