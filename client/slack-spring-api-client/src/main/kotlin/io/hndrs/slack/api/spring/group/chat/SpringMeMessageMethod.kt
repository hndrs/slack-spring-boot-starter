package io.hndrs.slack.api.spring.group.chat


import io.hndrs.slack.api.contract.jackson.group.chat.ChatMeMessageResponse
import io.hndrs.slack.api.contract.jackson.group.chat.ErrorChatMeMessageResponse
import io.hndrs.slack.api.contract.jackson.group.chat.SuccessfulChatMeMessageResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.chat.ChatMeMessageMethod
import io.hndrs.slack.api.group.chat.ChatMethodGroup
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * Spring based implementation of [ChatMethodGroup.meMessage]
 */
@Suppress("UNCHECKED_CAST")
class SpringMeMessageMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()
) : ChatMeMessageMethod() {

    override fun request(): ApiCallResult<SuccessfulChatMeMessageResponse, ErrorChatMeMessageResponse> {
        val response = SlackRequestBuilder<ChatMeMessageResponse>(authToken, restTemplate)
            .with(this.params)
            .toMethod("chat.meMessage")
            .returnAsType(ChatMeMessageResponse::class.java)
            .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulChatMeMessageResponse -> {
                val responseEntity = response.body as SuccessfulChatMeMessageResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorChatMeMessageResponse -> {
                val responseEntity = response.body as ErrorChatMeMessageResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
