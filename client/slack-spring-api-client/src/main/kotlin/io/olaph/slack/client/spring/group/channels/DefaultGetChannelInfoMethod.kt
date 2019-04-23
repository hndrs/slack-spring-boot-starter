package io.olaph.slack.client.spring.group.channels


import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.channels.ChannelsInfoMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.channels.ErrorGetChannelInfoResponse
import io.olaph.slack.dto.jackson.group.channels.SlackGetChannelInfoResponse
import io.olaph.slack.dto.jackson.group.channels.SuccessfulGetChannelInfoResponse
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultGetChannelInfoMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ChannelsInfoMethod() {

    override fun request(): ApiCallResult<SuccessfulGetChannelInfoResponse, ErrorGetChannelInfoResponse> {
        val response = SlackRequestBuilder<SlackGetChannelInfoResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("channels.info")
                .returnAsType(SlackGetChannelInfoResponse::class.java)
                .postUrlEncoded(mapOf(Pair("token", authToken), Pair("channel", this.params.channel)))

        return when (response.body!!) {
            is SuccessfulGetChannelInfoResponse -> {
                val responseEntity = response.body as SuccessfulGetChannelInfoResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorGetChannelInfoResponse -> {
                val responseEntity = response.body as ErrorGetChannelInfoResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
