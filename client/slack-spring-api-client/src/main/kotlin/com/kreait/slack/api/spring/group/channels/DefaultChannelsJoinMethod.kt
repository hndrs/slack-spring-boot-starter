package com.kreait.slack.api.spring.group.channels


import com.kreait.slack.api.contract.jackson.group.channels.ChannelsJoinResponse
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsJoinResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsJoinResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsJoinMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultsChannelJoinMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ChannelsJoinMethod() {

    override fun request(): ApiCallResult<SuccessfulChannelsJoinResponse, ErrorChannelsJoinResponse> {
        val response = SlackRequestBuilder<ChannelsJoinResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("channels.join")
                .returnAsType(ChannelsJoinResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulChannelsJoinResponse -> {
                val responseEntity = response.body as SuccessfulChannelsJoinResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorChannelsJoinResponse -> {
                val responseEntity = response.body as ErrorChannelsJoinResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
