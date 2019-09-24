package com.kreait.slack.api.spring.group.channels


import com.kreait.slack.api.contract.jackson.group.channels.ChannelsLeaveResponse
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsLeaveResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsLeaveResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsLeaveMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * Spring based implementation of [ChannelsMethodGroup.leave]
 */
@Suppress("UNCHECKED_CAST")
class SpringChannelsLeaveMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ChannelsLeaveMethod() {

    override fun request(): ApiCallResult<SuccessfulChannelsLeaveResponse, ErrorChannelsLeaveResponse> {

        val response = SlackRequestBuilder<ChannelsLeaveResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("channels.leave")
                .returnAsType(ChannelsLeaveResponse::class.java)
                .postWithJsonBody()
        return when (response.body!!) {
            is SuccessfulChannelsLeaveResponse -> {
                val responseEntity = response.body as SuccessfulChannelsLeaveResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorChannelsLeaveResponse -> {
                val responseEntity = response.body as ErrorChannelsLeaveResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
