package io.hndrs.slack.api.spring.group.users

import io.hndrs.slack.api.contract.jackson.group.users.ErrorListResponse
import io.hndrs.slack.api.contract.jackson.group.users.ListResponse
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulListResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.users.UserListMethod
import io.hndrs.slack.api.group.users.UsersMethodGroup
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * Spring based implementation of [UsersMethodGroup.list]
 */
@Suppress("UNCHECKED_CAST")
class SpringUserListMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()
) : UserListMethod() {

    override fun request(): ApiCallResult<SuccessfulListResponse, ErrorListResponse> {
        val response = SlackRequestBuilder<ListResponse>(authToken, restTemplate)
            .toMethod("users.list")
            .returnAsType(ListResponse::class.java)
            .postUrlEncoded(this.params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulListResponse -> {
                val successfulListResponse = response.body as SuccessfulListResponse
                this.onSuccess?.invoke(successfulListResponse)
                ApiCallResult(success = successfulListResponse)
            }
            is ErrorListResponse -> {
                val errorListResponse = response.body as ErrorListResponse
                this.onFailure?.invoke(errorListResponse)
                ApiCallResult(failure = errorListResponse)
            }
        }
    }
}
