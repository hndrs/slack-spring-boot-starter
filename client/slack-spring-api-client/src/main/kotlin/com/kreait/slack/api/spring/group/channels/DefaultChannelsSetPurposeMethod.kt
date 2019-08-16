package com.kreait.slack.api.spring.group.channels


import com.kreait.slack.api.contract.jackson.group.channels.ChannelsSetPurposeResponse
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsSetPurposeResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsSetPurposeResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsSetPurposeMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultChannelsSetPurposeMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ChannelsSetPurposeMethod() {
    override fun request(): ApiCallResult<SuccessfulChannelsSetPurposeResponse, ErrorChannelsSetPurposeResponse> {

        val response = SlackRequestBuilder<ChannelsSetPurposeResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("channels.setPurpose")
                .returnAsType(ChannelsSetPurposeResponse::class.java)
                .postWithJsonBody()
        return when (response.body!!) {
            is SuccessfulChannelsSetPurposeResponse -> {
                val responseEntity = response.body as SuccessfulChannelsSetPurposeResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorChannelsSetPurposeResponse -> {
                val responseEntity = response.body as ErrorChannelsSetPurposeResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
