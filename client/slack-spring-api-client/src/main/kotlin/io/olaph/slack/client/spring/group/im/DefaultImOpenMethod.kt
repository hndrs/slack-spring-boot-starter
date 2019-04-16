package io.olaph.slack.client.spring.group.im

import io.olaph.slack.client.UnknownResponseException
import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.im.ImOpenMethod
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.im.ErrorImOpenResponse
import io.olaph.slack.dto.jackson.group.im.SlackImOpenResponse
import io.olaph.slack.dto.jackson.group.im.SuccessfulImOpenResponse
import org.springframework.web.client.RestTemplate
import org.springframework.http.client.BufferingClientHttpRequestFactory
import org.springframework.http.client.SimpleClientHttpRequestFactory


/**
 * https://api.slack.com/methods/im.open
 */
@Suppress("UNCHECKED_CAST")
class DefaultImOpenMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplate(BufferingClientHttpRequestFactory(SimpleClientHttpRequestFactory()))) : ImOpenMethod() {

    override fun request(): ApiCallResult<SuccessfulImOpenResponse, ErrorImOpenResponse> {
        val response = SlackRequestBuilder<SlackImOpenResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("im.open")
                .returnAsType(SlackImOpenResponse::class.java)
                .postWithJsonBody()

        return when {
            response.body is SuccessfulImOpenResponse -> {
                val responseEntity = response.body as SuccessfulImOpenResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            response.body is ErrorImOpenResponse -> {
                val responseEntity = response.body as ErrorImOpenResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
            else -> {
                throw UnknownResponseException(this::class, response)
            }
        }
    }
}
