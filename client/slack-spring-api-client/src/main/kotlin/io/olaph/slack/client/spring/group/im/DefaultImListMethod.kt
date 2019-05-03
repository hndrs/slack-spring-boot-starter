package io.olaph.slack.client.spring.group.im


import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.im.ImListMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.im.ErrorImListResponse
import io.olaph.slack.dto.jackson.group.im.SlackImListResponse
import io.olaph.slack.dto.jackson.group.im.SuccessfulImListResponse
import org.springframework.web.client.RestTemplate


/**
 * https://api.slack.com/methods/im.List
 */
@Suppress("UNCHECKED_CAST")
class DefaultImListMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ImListMethod() {

    override fun request(): ApiCallResult<SuccessfulImListResponse, ErrorImListResponse> {
        val response = SlackRequestBuilder<SlackImListResponse>(authToken, restTemplate)
                .toMethod("im.list")
                .returnAsType(SlackImListResponse::class.java)
                .postUrlEncoded(this.params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulImListResponse -> {
                val responseEntity = response.body as SuccessfulImListResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorImListResponse -> {
                val responseEntity = response.body as ErrorImListResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
