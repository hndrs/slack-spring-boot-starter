package com.kreait.slack.api.spring.group.channels


import com.kreait.slack.api.contract.jackson.group.channels.ErrorGetChannelInfoResponse
import com.kreait.slack.api.contract.jackson.group.channels.SlackGetChannelInfoResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulGetChannelInfoResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsInfoMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultGetChannelInfoMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ChannelsInfoMethod() {

    override fun request(): ApiCallResult<SuccessfulGetChannelInfoResponse, ErrorGetChannelInfoResponse> {
        val response = SlackRequestBuilder<SlackGetChannelInfoResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("channels.info")
                .returnAsType(SlackGetChannelInfoResponse::class.java)
                .postUrlEncoded(mapOf(Pair("token", authToken), Pair("channel", this.params.channel)))

        return when (response.body!!) {
            is SuccessfulGetChannelInfoResponse -> {
                val responseEntity = response.body as SuccessfulGetChannelInfoResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorGetChannelInfoResponse -> {
                val responseEntity = response.body as ErrorGetChannelInfoResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
