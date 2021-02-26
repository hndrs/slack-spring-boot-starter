package io.hndrs.slack.api.spring.group.conversations


/**
 * https://api.slack.com/methods/conversations.members
 */
import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationMembersResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationMembersResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationMembersResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.conversations.ConversationsMembersMethod
import io.hndrs.slack.api.group.conversations.ConversationsMethodGroup
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [ConversationsMethodGroup.members]
 */
@Suppress("UNCHECKED_CAST")
class SpringConversationsMembersMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate()
) : io.hndrs.slack.api.group.conversations.ConversationsMembersMethod() {

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulConversationMembersResponse, ErrorConversationMembersResponse> {
        val response = SlackRequestBuilder<ConversationMembersResponse>(authToken, restTemplate)
            .toMethod("conversations.members")
            .returnAsType(ConversationMembersResponse::class.java)
            .postUrlEncoded(this.params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulConversationMembersResponse -> {
                val responseEntity = response.body as SuccessfulConversationMembersResponse
                this.onSuccess?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(success = responseEntity)
            }
            is ErrorConversationMembersResponse -> {
                val responseEntity = response.body as ErrorConversationMembersResponse
                this.onFailure?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(failure = responseEntity)
            }
        }
    }
}
