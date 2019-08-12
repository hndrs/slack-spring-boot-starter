package com.kreait.slack.api.spring.group.channels


import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelArchiveResponse
import com.kreait.slack.api.contract.jackson.group.channels.ChannelArchiveResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelArchiveResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsArchiveMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultChannelsArchiveMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ChannelsArchiveMethod() {
    override fun request(): ApiCallResult<SuccessfulChannelArchiveResponse, ErrorChannelArchiveResponse> {
        val response = SlackRequestBuilder<ChannelArchiveResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("channels.archive")
                .returnAsType(ChannelArchiveResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulChannelArchiveResponse -> {
                val responseEntity = response.body as SuccessfulChannelArchiveResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorChannelArchiveResponse -> {
                val responseEntity = response.body as ErrorChannelArchiveResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
