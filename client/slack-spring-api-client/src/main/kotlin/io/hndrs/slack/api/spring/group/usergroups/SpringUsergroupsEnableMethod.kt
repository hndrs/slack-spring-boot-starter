package io.hndrs.slack.api.spring.group.usergroups


/**
 * Spring based implementation of [UsergroupsMethodGroup.enable]
 */
import io.hndrs.slack.api.contract.jackson.group.usergroups.EnableResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.ErrorEnableResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.SuccessfulEnableResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.usergroups.UsergroupsEnableMethod
import io.hndrs.slack.api.group.users.UsersMethodGroup
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * Spring based implementation of [UsersMethodGroup.conversations]
 */
@Suppress("UNCHECKED_CAST")
class SpringUsergroupsEnableMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()
) : UsergroupsEnableMethod() {

    override fun request(): ApiCallResult<SuccessfulEnableResponse, ErrorEnableResponse> {
        val response = SlackRequestBuilder<EnableResponse>(authToken, restTemplate)
            .toMethod("usergroups.enable")
            .returnAsType(EnableResponse::class.java)
            .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulEnableResponse -> {
                val responseEntity = response.body as SuccessfulEnableResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorEnableResponse -> {
                val responseEntity = response.body as ErrorEnableResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
