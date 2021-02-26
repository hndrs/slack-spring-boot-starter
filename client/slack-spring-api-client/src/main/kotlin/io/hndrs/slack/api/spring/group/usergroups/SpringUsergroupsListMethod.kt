package io.hndrs.slack.api.spring.group.usergroups

/**
 * Spring based implementation of [UsergroupsMethodGroup.list]
 */
import io.hndrs.slack.api.contract.jackson.group.usergroups.ErrorListResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.ListResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.SuccessfulListResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.usergroups.UsergroupsListMethod
import io.hndrs.slack.api.group.users.UsersMethodGroup
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * Spring based implementation of [UsersMethodGroup.conversations]
 */
@Suppress("UNCHECKED_CAST")
class SpringUsergroupsListMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate()
) : io.hndrs.slack.api.group.usergroups.UsergroupsListMethod() {

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulListResponse, ErrorListResponse> {
        val response = SlackRequestBuilder<ListResponse>(authToken, restTemplate)
            .toMethod("usergroups.list")
            .returnAsType(ListResponse::class.java)
            .postUrlEncoded(this.params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulListResponse -> {
                val responseEntity = response.body as SuccessfulListResponse
                this.onSuccess?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(success = responseEntity)
            }
            is ErrorListResponse -> {
                val responseEntity = response.body as ErrorListResponse
                this.onFailure?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(failure = responseEntity)
            }
        }
    }
}
