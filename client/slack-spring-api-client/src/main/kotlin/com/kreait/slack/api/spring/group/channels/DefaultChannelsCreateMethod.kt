package com.kreait.slack.api.spring.group.channels


import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsCreateResponse
import com.kreait.slack.api.contract.jackson.group.channels.ChannelsCreateResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsCreateResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsCreateMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultChannelsCreateMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ChannelsCreateMethod() {
    override fun request(): ApiCallResult<SuccessfulChannelsCreateResponse, ErrorChannelsCreateResponse> {

        val response = SlackRequestBuilder<ChannelsCreateResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("channels.create")
                .returnAsType(ChannelsCreateResponse::class.java)
                .postWithJsonBody()
        return when (response.body!!) {
            is SuccessfulChannelsCreateResponse -> {
                val responseEntity = response.body as SuccessfulChannelsCreateResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorChannelsCreateResponse -> {
                val responseEntity = response.body as ErrorChannelsCreateResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
