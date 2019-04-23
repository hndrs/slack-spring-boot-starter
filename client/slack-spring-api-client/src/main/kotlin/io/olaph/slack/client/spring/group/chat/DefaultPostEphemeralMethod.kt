package io.olaph.slack.client.spring.group.chat


import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.chat.ChatPostEphemeralMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.chat.ErrorPostEphemeralResponse
import io.olaph.slack.dto.jackson.group.chat.SlackPostEphemeralResponse
import io.olaph.slack.dto.jackson.group.chat.SuccessfulPostEphemeralResponse
import org.springframework.web.client.RestTemplate


/**
 * Posts a Ephemeral message to a channel which is only visible to a specific user
 * @param config the configuration of the Slackclient
 * @return the API Call Method containing the ResponseEntities
 */
@Suppress("UNCHECKED_CAST")
class DefaultPostEphemeralMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ChatPostEphemeralMethod() {

    override fun request(): ApiCallResult<SuccessfulPostEphemeralResponse, ErrorPostEphemeralResponse> {
        val response = SlackRequestBuilder<SlackPostEphemeralResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("chat.postEphemeral")
                .returnAsType(SlackPostEphemeralResponse::class.java)
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
