package io.hndrs.slack.api.spring.group.chat


import io.hndrs.slack.api.contract.jackson.group.chat.ChatGetPermalinkResponse
import io.hndrs.slack.api.contract.jackson.group.chat.ErrorChatGetPermalinkResponse
import io.hndrs.slack.api.contract.jackson.group.chat.SuccessfulChatGetPermalinkResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.chat.ChatGetPermalinkMethod
import io.hndrs.slack.api.group.chat.ChatMethodGroup
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * Spring based implementation of [ChatMethodGroup.getPermalink]
 */
@Suppress("UNCHECKED_CAST")
class SpringGetPermalinkMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()
) : ChatGetPermalinkMethod() {

    override fun request(): ApiCallResult<SuccessfulChatGetPermalinkResponse, ErrorChatGetPermalinkResponse> {
        val response = SlackRequestBuilder<ChatGetPermalinkResponse>(authToken, restTemplate)
            .with(this.params)
            .toMethod("chat.getPermalink")
            .returnAsType(ChatGetPermalinkResponse::class.java)
            .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulChatGetPermalinkResponse -> {
                val responseEntity = response.body as SuccessfulChatGetPermalinkResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorChatGetPermalinkResponse -> {
                val responseEntity = response.body as ErrorChatGetPermalinkResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
