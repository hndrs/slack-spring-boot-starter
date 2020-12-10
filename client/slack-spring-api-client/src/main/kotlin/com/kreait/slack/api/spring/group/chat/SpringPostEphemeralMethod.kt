package com.kreait.slack.api.spring.group.chat


import com.kreait.slack.api.contract.jackson.group.chat.ErrorPostEphemeralResponse
import com.kreait.slack.api.contract.jackson.group.chat.PostEphemeralResponse
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulPostEphemeralResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.chat.ChatMethodGroup
import com.kreait.slack.api.group.chat.ChatPostEphemeralMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [ChatMethodGroup.postEphemeral]
 */
@Suppress("UNCHECKED_CAST")
class SpringPostEphemeralMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()
) : ChatPostEphemeralMethod() {

    override fun request(): ApiCallResult<SuccessfulPostEphemeralResponse, ErrorPostEphemeralResponse> {
        val response = SlackRequestBuilder<PostEphemeralResponse>(authToken, restTemplate)
            .with(this.params)
            .toMethod("chat.postEphemeral")
            .returnAsType(PostEphemeralResponse::class.java)
            .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulPostEphemeralResponse -> {
                val responseEntity = response.body as SuccessfulPostEphemeralResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorPostEphemeralResponse -> {
                val responseEntity = response.body as ErrorPostEphemeralResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
