package io.hndrs.slack.api.spring.group.usergroups


/**
 * Spring based implementation of [UsergroupsMethodGroup.disable]
 */
import io.hndrs.slack.api.contract.jackson.group.usergroups.DisableResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.ErrorDisableResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.SuccessfulDisableResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.usergroups.UsergroupsDisableMethod
import io.hndrs.slack.api.group.users.UsersMethodGroup
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * Spring based implementation of [UsersMethodGroup.conversations]
 */
@Suppress("UNCHECKED_CAST")
class SpringUsergroupsDisableMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate()
) : io.hndrs.slack.api.group.usergroups.UsergroupsDisableMethod() {

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulDisableResponse, ErrorDisableResponse> {
        val response = SlackRequestBuilder<DisableResponse>(authToken, restTemplate)
            .toMethod("usergroups.disable")
            .returnAsType(DisableResponse::class.java)
            .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulDisableResponse -> {
                val responseEntity = response.body as SuccessfulDisableResponse
                this.onSuccess?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(success = responseEntity)
            }
            is ErrorDisableResponse -> {
                val responseEntity = response.body as ErrorDisableResponse
                this.onFailure?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(failure = responseEntity)
            }
        }
    }
}
