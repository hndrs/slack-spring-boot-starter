package com.kreait.slack.api.spring.group.channels


import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsCreateMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelCreateResponse
import com.kreait.slack.api.contract.jackson.group.channels.SlackChannelCreateResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelCreateResponse
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultChannelCreateMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ChannelsCreateMethod() {
    override fun request(): ApiCallResult<SuccessfulChannelCreateResponse, ErrorChannelCreateResponse> {

        val response = SlackRequestBuilder<SlackChannelCreateResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("channels.create")
                .returnAsType(SlackChannelCreateResponse::class.java)
                .postWithJsonBody()
        return when (response.body!!) {
            is SuccessfulChannelCreateResponse -> {
                val responseEntity = response.body as SuccessfulChannelCreateResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorChannelCreateResponse -> {
                val responseEntity = response.body as ErrorChannelCreateResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
