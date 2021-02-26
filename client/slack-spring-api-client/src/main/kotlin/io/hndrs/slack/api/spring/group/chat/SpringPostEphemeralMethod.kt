package io.hndrs.slack.api.spring.group.chat


import io.hndrs.slack.api.contract.jackson.group.chat.ErrorPostEphemeralResponse
import io.hndrs.slack.api.contract.jackson.group.chat.PostEphemeralResponse
import io.hndrs.slack.api.contract.jackson.group.chat.SuccessfulPostEphemeralResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.chat.ChatMethodGroup
import io.hndrs.slack.api.group.chat.ChatPostEphemeralMethod
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [ChatMethodGroup.postEphemeral]
 */
@Suppress("UNCHECKED_CAST")
class SpringPostEphemeralMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate()
) : io.hndrs.slack.api.group.chat.ChatPostEphemeralMethod() {

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulPostEphemeralResponse, ErrorPostEphemeralResponse> {
        val response = SlackRequestBuilder<PostEphemeralResponse>(authToken, restTemplate)
            .with(this.params)
            .toMethod("chat.postEphemeral")
            .returnAsType(PostEphemeralResponse::class.java)
            .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulPostEphemeralResponse -> {
                val responseEntity = response.body as SuccessfulPostEphemeralResponse
                this.onSuccess?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(success = responseEntity)
            }
            is ErrorPostEphemeralResponse -> {
                val responseEntity = response.body as ErrorPostEphemeralResponse
                this.onFailure?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(failure = responseEntity)
            }
        }
    }
}
