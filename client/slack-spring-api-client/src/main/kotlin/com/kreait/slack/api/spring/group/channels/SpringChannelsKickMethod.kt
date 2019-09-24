package com.kreait.slack.api.spring.group.channels


import com.kreait.slack.api.contract.jackson.group.channels.ChannelKickResponse
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelKickResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelKickResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsKickMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * Spring based implementation of [ChannelsMethodGroup.kick]
 */
@Suppress("UNCHECKED_CAST")
class SpringChannelsKickMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ChannelsKickMethod() {

    override fun request(): ApiCallResult<SuccessfulChannelKickResponse, ErrorChannelKickResponse> {
        val response = SlackRequestBuilder<ChannelKickResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("channels.kick")
                .returnAsType(ChannelKickResponse::class.java)
                .postUrlEncoded(this.params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulChannelKickResponse -> {
                val responseEntity = response.body as SuccessfulChannelKickResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorChannelKickResponse -> {
                val responseEntity = response.body as ErrorChannelKickResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
