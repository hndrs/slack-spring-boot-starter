package com.kreait.slack.api.spring.group.channels


import com.kreait.slack.api.contract.jackson.group.channels.ChannelRenameResponse
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelRenameResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelRenameResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsRenameMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultChannelRenameMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ChannelsRenameMethod() {
    override fun request(): ApiCallResult<SuccessfulChannelRenameResponse, ErrorChannelRenameResponse> {

        val response = SlackRequestBuilder<ChannelRenameResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("channels.rename")
                .returnAsType(ChannelRenameResponse::class.java)
                .postWithJsonBody()
        return when (response.body!!) {
            is SuccessfulChannelRenameResponse -> {
                val responseEntity = response.body as SuccessfulChannelRenameResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorChannelRenameResponse -> {
                val responseEntity = response.body as ErrorChannelRenameResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
