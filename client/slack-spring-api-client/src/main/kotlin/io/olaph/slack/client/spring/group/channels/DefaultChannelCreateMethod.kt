package io.olaph.slack.client.spring.group.channels

import io.olaph.slack.client.UnknownResponseException
import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.channels.ChannelsCreateMethod
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.channels.ErrorChannelCreateResponse
import io.olaph.slack.dto.jackson.group.channels.SlackChannelCreateResponse
import io.olaph.slack.dto.jackson.group.channels.SuccessfulChannelCreateResponse
import org.springframework.web.client.RestTemplate
import org.springframework.http.client.BufferingClientHttpRequestFactory
import org.springframework.http.client.SimpleClientHttpRequestFactory



@Suppress("UNCHECKED_CAST")
class DefaultChannelCreateMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplate(BufferingClientHttpRequestFactory(SimpleClientHttpRequestFactory()))) : ChannelsCreateMethod() {
    override fun request(): ApiCallResult<SuccessfulChannelCreateResponse, ErrorChannelCreateResponse> {

        val response = SlackRequestBuilder<SlackChannelCreateResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("channels.create")
                .returnAsType(SlackChannelCreateResponse::class.java)
                .postWithJsonBody()

        return when {
            response.body is SuccessfulChannelCreateResponse -> {
                val responseEntity = response.body as SuccessfulChannelCreateResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            response.body is ErrorChannelCreateResponse -> {
                val responseEntity = response.body as ErrorChannelCreateResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
            else -> {
                throw UnknownResponseException(this::class, response)
            }
        }
    }
}
