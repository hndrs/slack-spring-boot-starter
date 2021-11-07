package io.hndrs.slack.api.spring.group.chat


import io.hndrs.slack.api.contract.jackson.group.chat.ChatDeleteResponse
import io.hndrs.slack.api.contract.jackson.group.chat.ErrorChatDeleteResponse
import io.hndrs.slack.api.contract.jackson.group.chat.SuccessfulChatDeleteResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.chat.ChatDeleteMethod
import io.hndrs.slack.api.group.chat.ChatMethodGroup
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [ChatMethodGroup.delete]
 */
@Suppress("UNCHECKED_CAST")
class SpringDeleteMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()
) : ChatDeleteMethod() {

    override fun request(): ApiCallResult<SuccessfulChatDeleteResponse, ErrorChatDeleteResponse> {
        val response = SlackRequestBuilder<ChatDeleteResponse>(authToken, restTemplate)
            .with(this.params)
            .toMethod("chat.delete")
            .returnAsType(ChatDeleteResponse::class.java)
            .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulChatDeleteResponse -> {
                val responseEntity = response.body as SuccessfulChatDeleteResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorChatDeleteResponse -> {
                val responseEntity = response.body as ErrorChatDeleteResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
