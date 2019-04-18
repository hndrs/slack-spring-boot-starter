package io.olaph.slack.client.spring.group.im

import io.olaph.slack.client.ErrorResponseException
import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.im.ImMarkMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.im.ErrorImMarkResponse
import io.olaph.slack.dto.jackson.group.im.SlackImMarkResponse
import io.olaph.slack.dto.jackson.group.im.SuccessfulImMarkResponse
import org.springframework.web.client.RestTemplate


/**
 * https://api.slack.com/methods/im.mark
 */

@Suppress("UNCHECKED_CAST")
class DefaultImMarkMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ImMarkMethod() {

    override fun request(): ApiCallResult<SuccessfulImMarkResponse, ErrorImMarkResponse> {
        val response = SlackRequestBuilder<SlackImMarkResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("im.mark")
                .returnAsType(SlackImMarkResponse::class.java)
                .postWithJsonBody()

        if (!response.statusCode.is2xxSuccessful) {
            throw ErrorResponseException(this::class, response.statusCode.name)
        }

        return when (response.body!!) {
            is SuccessfulImMarkResponse -> {
                val responseEntity = response.body as SuccessfulImMarkResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorImMarkResponse -> {
                val responseEntity = response.body as ErrorImMarkResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
