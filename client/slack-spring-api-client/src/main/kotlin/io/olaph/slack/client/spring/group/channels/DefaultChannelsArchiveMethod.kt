package io.olaph.slack.client.spring.group.channels

import io.olaph.slack.client.ErrorResponseException
import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.channels.ChannelsArchiveMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.channels.ErrorChannelArchiveResponse
import io.olaph.slack.dto.jackson.group.channels.SlackGetChannelArchiveResponse
import io.olaph.slack.dto.jackson.group.channels.SuccessfulChannelArchiveResponse
import org.springframework.web.client.RestTemplate
import org.springframework.http.client.BufferingClientHttpRequestFactory
import org.springframework.http.client.SimpleClientHttpRequestFactory


@Suppress("UNCHECKED_CAST")
class DefaultChannelsArchiveMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ChannelsArchiveMethod() {
    override fun request(): ApiCallResult<SuccessfulChannelArchiveResponse, ErrorChannelArchiveResponse> {
        val response = SlackRequestBuilder<SlackGetChannelArchiveResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("channels.archive")
                .returnAsType(SlackGetChannelArchiveResponse::class.java)
                .postWithJsonBody()

        if (!response.statusCode.is2xxSuccessful) {
            throw ErrorResponseException(this::class, response.statusCode.name)
        }

        return when (response.body!!) {
            is SuccessfulChannelArchiveResponse -> {
                val responseEntity = response.body as SuccessfulChannelArchiveResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorChannelArchiveResponse -> {
                val responseEntity = response.body as ErrorChannelArchiveResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
