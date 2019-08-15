package com.kreait.slack.api.spring.group.channels


import com.kreait.slack.api.contract.jackson.group.channels.ChannelSetPurposeResponse
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelSetPurposeResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelSetPurposeResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsSetPurposeMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultChannelSetPurposeMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ChannelsSetPurposeMethod() {
    override fun request(): ApiCallResult<SuccessfulChannelSetPurposeResponse, ErrorChannelSetPurposeResponse> {

        val response = SlackRequestBuilder<ChannelSetPurposeResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("channels.setPurpose")
                .returnAsType(ChannelSetPurposeResponse::class.java)
                .postWithJsonBody()
        return when (response.body!!) {
            is SuccessfulChannelSetPurposeResponse -> {
                val responseEntity = response.body as SuccessfulChannelSetPurposeResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorChannelSetPurposeResponse -> {
                val responseEntity = response.body as ErrorChannelSetPurposeResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
