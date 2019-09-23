package com.kreait.slack.api.spring.group.channels


import com.kreait.slack.api.contract.jackson.group.channels.ChannelsHistoryResponse
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsHistoryResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsHistoryResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsHistoryMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class SpringChannelsHistoryMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ChannelsHistoryMethod() {

    override fun request(): ApiCallResult<SuccessfulChannelsHistoryResponse, ErrorChannelsHistoryResponse> {
        val response = SlackRequestBuilder<ChannelsHistoryResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("channels.history")
                .returnAsType(ChannelsHistoryResponse::class.java)
                .postUrlEncoded(params.toRequestMap().plus(Pair("token", authToken)))


        return when (response.body!!) {
            is SuccessfulChannelsHistoryResponse -> {
                val responseEntity = response.body as SuccessfulChannelsHistoryResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorChannelsHistoryResponse -> {
                val responseEntity = response.body as ErrorChannelsHistoryResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
