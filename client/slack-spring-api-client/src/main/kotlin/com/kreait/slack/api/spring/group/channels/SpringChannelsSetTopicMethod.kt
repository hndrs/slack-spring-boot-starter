package com.kreait.slack.api.spring.group.channels


import com.kreait.slack.api.contract.jackson.group.channels.ChannelsSetTopicResponse
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsSetTopicResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsSetTopicResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsSetTopicMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * Spring based implementation of [ChannelsMethodGroup.setTopic]
 */
@Suppress("UNCHECKED_CAST")
class SpringChannelsSetTopicMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ChannelsSetTopicMethod() {
    override fun request(): ApiCallResult<SuccessfulChannelsSetTopicResponse, ErrorChannelsSetTopicResponse> {

        val response = SlackRequestBuilder<ChannelsSetTopicResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("channels.setTopic")
                .returnAsType(ChannelsSetTopicResponse::class.java)
                .postWithJsonBody()
        return when (response.body!!) {
            is SuccessfulChannelsSetTopicResponse -> {
                val responseEntity = response.body as SuccessfulChannelsSetTopicResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorChannelsSetTopicResponse -> {
                val responseEntity = response.body as ErrorChannelsSetTopicResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
