package com.kreait.slack.api.spring.group.channels


import com.kreait.slack.api.contract.jackson.group.channels.ChannelUnarchiveResponse
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelUnarchiveResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelUnarchiveResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsUnarchiveMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * Spring based implementation of [ChannelsMethodGroup.unarchive]
 */
@Suppress("UNCHECKED_CAST")
class SpringChannelsUnarchiveMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ChannelsUnarchiveMethod() {
    override fun request(): ApiCallResult<SuccessfulChannelUnarchiveResponse, ErrorChannelUnarchiveResponse> {
        val response = SlackRequestBuilder<ChannelUnarchiveResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("channels.unarchive")
                .returnAsType(ChannelUnarchiveResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulChannelUnarchiveResponse -> {
                val responseEntity = response.body as SuccessfulChannelUnarchiveResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorChannelUnarchiveResponse -> {
                val responseEntity = response.body as ErrorChannelUnarchiveResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
