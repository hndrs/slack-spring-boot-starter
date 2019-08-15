package com.kreait.slack.api.spring.group.channels


import com.kreait.slack.api.contract.jackson.group.channels.ChannelHistoryResponse
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelHistoryResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelHistoryResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsHistoryMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultChannelHistoryMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ChannelsHistoryMethod() {

    override fun request(): ApiCallResult<SuccessfulChannelHistoryResponse, ErrorChannelHistoryResponse> {
        val response = SlackRequestBuilder<ChannelHistoryResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("channels.history")
                .returnAsType(ChannelHistoryResponse::class.java)
                .postUrlEncoded(params.toRequestMap().plus(Pair("token", authToken)))


        return when (response.body!!) {
            is SuccessfulChannelHistoryResponse -> {
                val responseEntity = response.body as SuccessfulChannelHistoryResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorChannelHistoryResponse -> {
                val responseEntity = response.body as ErrorChannelHistoryResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
