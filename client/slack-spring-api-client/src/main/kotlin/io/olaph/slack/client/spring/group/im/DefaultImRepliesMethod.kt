package io.olaph.slack.client.spring.group.im


import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.im.ImRepliesMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.im.ErrorImRepliesResponse
import io.olaph.slack.dto.jackson.group.im.SlackImRepliesResponse
import io.olaph.slack.dto.jackson.group.im.SuccessfulImRepliesResponse
import org.springframework.web.client.RestTemplate


/**
 * https://api.slack.com/methods/im.replies
 */

@Suppress("UNCHECKED_CAST")
class DefaultImRepliesMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ImRepliesMethod() {
    override fun request(): ApiCallResult<SuccessfulImRepliesResponse, ErrorImRepliesResponse> {
        val response = SlackRequestBuilder<SlackImRepliesResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("im.replies")
                .returnAsType(SlackImRepliesResponse::class.java)
                .postUrlEncoded(this.params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulImRepliesResponse -> {
                val responseEntity = response.body as SuccessfulImRepliesResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorImRepliesResponse -> {
                val responseEntity = response.body as ErrorImRepliesResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
