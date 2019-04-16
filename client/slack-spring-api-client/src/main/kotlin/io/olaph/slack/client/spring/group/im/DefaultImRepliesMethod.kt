package io.olaph.slack.client.spring.group.im

import io.olaph.slack.client.UnknownResponseException
import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.im.ImRepliesMethod
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.im.ErrorImRepliesResponse
import io.olaph.slack.dto.jackson.group.im.SlackImRepliesResponse
import io.olaph.slack.dto.jackson.group.im.SuccessfulImRepliesResponse
import org.springframework.web.client.RestTemplate
import org.springframework.http.client.BufferingClientHttpRequestFactory
import org.springframework.http.client.SimpleClientHttpRequestFactory


/**
 * https://api.slack.com/methods/im.replies
 */

@Suppress("UNCHECKED_CAST")
class DefaultImRepliesMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplate(BufferingClientHttpRequestFactory(SimpleClientHttpRequestFactory()))) : ImRepliesMethod() {
    override fun request(): ApiCallResult<SuccessfulImRepliesResponse, ErrorImRepliesResponse> {
        val response = SlackRequestBuilder<SlackImRepliesResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("im.replies")
                .returnAsType(SlackImRepliesResponse::class.java)
                .postUrlEncoded(this.params.toRequestMap())

        return when {
            response.body is SuccessfulImRepliesResponse -> {
                val responseEntity = response.body as SuccessfulImRepliesResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            response.body is ErrorImRepliesResponse -> {
                val responseEntity = response.body as ErrorImRepliesResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
            else -> {
                throw UnknownResponseException(this::class, response)
            }
        }
    }
}
