package com.kreait.slack.api.spring.group.channels


import com.kreait.slack.api.contract.jackson.group.channels.ChannelMarkResponse
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelMarkResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelMarkResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsMarkMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultChannelMarkMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ChannelsMarkMethod() {
    override fun request(): ApiCallResult<SuccessfulChannelMarkResponse, ErrorChannelMarkResponse> {
        val response = SlackRequestBuilder<ChannelMarkResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("channels.mark")
                .returnAsType(ChannelMarkResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulChannelMarkResponse -> {
                val responseEntity = response.body as SuccessfulChannelMarkResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorChannelMarkResponse -> {
                val responseEntity = response.body as ErrorChannelMarkResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
