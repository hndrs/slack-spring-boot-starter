package io.olaph.slack.client.spring.group.im

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.im.ImHistoryMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.im.ErrorImHistoryResponse
import io.olaph.slack.dto.jackson.group.im.SlackImHistoryResponse
import io.olaph.slack.dto.jackson.group.im.SuccessfulImHistoryResponse
import org.springframework.web.client.RestTemplate

/**
 * https://api.slack.com/methods/im.history
 */
@Suppress("UNCHECKED_CAST")
class DefaultImHistoryMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ImHistoryMethod() {

    override fun request(): ApiCallResult<SuccessfulImHistoryResponse, ErrorImHistoryResponse> {
        val response = SlackRequestBuilder<SlackImHistoryResponse>(authToken, restTemplate)
                .toMethod("im.history")
                .returnAsType(SlackImHistoryResponse::class.java)
                .postUrlEncoded(this.params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulImHistoryResponse -> {
                val responseEntity = response.body as SuccessfulImHistoryResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorImHistoryResponse -> {
                val responseEntity = response.body as ErrorImHistoryResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}