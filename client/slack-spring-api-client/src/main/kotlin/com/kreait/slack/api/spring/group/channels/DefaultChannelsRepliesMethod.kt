package com.kreait.slack.api.spring.group.channels


import com.kreait.slack.api.contract.jackson.group.channels.ChannelsRepliesResponse
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsRepliesResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsRepliesResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsRepliesMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultChannelsRepliesMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ChannelsRepliesMethod() {
    override fun request(): ApiCallResult<SuccessfulChannelsRepliesResponse, ErrorChannelsRepliesResponse> {

        val response = SlackRequestBuilder<ChannelsRepliesResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("channels.replies")
                .returnAsType(ChannelsRepliesResponse::class.java)
                .postUrlEncoded(this.params.toRequestMap().plus("token" to authToken))
        return when (response.body!!) {
            is SuccessfulChannelsRepliesResponse -> {
                val responseEntity = response.body as SuccessfulChannelsRepliesResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorChannelsRepliesResponse -> {
                val responseEntity = response.body as ErrorChannelsRepliesResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
