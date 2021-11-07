package io.hndrs.slack.api.spring.group.chat


import io.hndrs.slack.api.contract.jackson.group.chat.ChatUpdateResponse
import io.hndrs.slack.api.contract.jackson.group.chat.ErrorChatUpdateResponse
import io.hndrs.slack.api.contract.jackson.group.chat.SuccessfulChatUpdateResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.chat.ChatMethodGroup
import io.hndrs.slack.api.group.chat.ChatUpdateMethod
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * Spring based implementation of [ChatMethodGroup.update]
 */
@Suppress("UNCHECKED_CAST")
class SpringUpdateMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()
) : ChatUpdateMethod() {

    override fun request(): ApiCallResult<SuccessfulChatUpdateResponse, ErrorChatUpdateResponse> {
        val response = SlackRequestBuilder<ChatUpdateResponse>(authToken, restTemplate)
            .with(this.params)
            .toMethod("chat.update")
            .returnAsType(ChatUpdateResponse::class.java)
            .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulChatUpdateResponse -> {
                val responseEntity = response.body as SuccessfulChatUpdateResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorChatUpdateResponse -> {
                val responseEntity = response.body as ErrorChatUpdateResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
