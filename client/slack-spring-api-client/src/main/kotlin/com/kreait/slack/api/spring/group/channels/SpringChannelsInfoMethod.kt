package com.kreait.slack.api.spring.group.channels


import com.kreait.slack.api.contract.jackson.group.channels.ChannelInfoResponse
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelInfoResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelInfoResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsInfoMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [ChannelsMethodGroup.info]
 */
@Suppress("UNCHECKED_CAST")
class SpringChannelsInfoMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ChannelsInfoMethod() {

    override fun request(): ApiCallResult<SuccessfulChannelInfoResponse, ErrorChannelInfoResponse> {
        val response = SlackRequestBuilder<ChannelInfoResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("channels.info")
                .returnAsType(ChannelInfoResponse::class.java)
                .postUrlEncoded(mapOf(Pair("token", authToken), Pair("channel", this.params.channel)))

        return when (response.body!!) {
            is SuccessfulChannelInfoResponse -> {
                val responseEntity = response.body as SuccessfulChannelInfoResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorChannelInfoResponse -> {
                val responseEntity = response.body as ErrorChannelInfoResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
