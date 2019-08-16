package com.kreait.slack.api.spring.group.channels


import com.kreait.slack.api.contract.jackson.group.channels.ChannelsMarkResponse
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsMarkResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsMarkResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsMarkMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultChannelsMarkMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ChannelsMarkMethod() {
    override fun request(): ApiCallResult<SuccessfulChannelsMarkResponse, ErrorChannelsMarkResponse> {
        val response = SlackRequestBuilder<ChannelsMarkResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("channels.mark")
                .returnAsType(ChannelsMarkResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulChannelsMarkResponse -> {
                val responseEntity = response.body as SuccessfulChannelsMarkResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorChannelsMarkResponse -> {
                val responseEntity = response.body as ErrorChannelsMarkResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
