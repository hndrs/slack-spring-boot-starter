package com.kreait.slack.api.spring.group.channels


import com.kreait.slack.api.contract.jackson.group.channels.ChannelRepliesResponse
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelRepliesResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelRepliesResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsRepliesMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultChannelRepliesMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ChannelsRepliesMethod() {
    override fun request(): ApiCallResult<SuccessfulChannelRepliesResponse, ErrorChannelRepliesResponse> {

        val response = SlackRequestBuilder<ChannelRepliesResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("channels.replies")
                .returnAsType(ChannelRepliesResponse::class.java)
                .postUrlEncoded(this.params.toRequestMap().plus("token" to authToken))
        return when (response.body!!) {
            is SuccessfulChannelRepliesResponse -> {
                val responseEntity = response.body as SuccessfulChannelRepliesResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorChannelRepliesResponse -> {
                val responseEntity = response.body as ErrorChannelRepliesResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
