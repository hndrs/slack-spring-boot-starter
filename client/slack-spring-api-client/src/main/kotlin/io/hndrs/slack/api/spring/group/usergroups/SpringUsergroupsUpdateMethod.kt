package io.hndrs.slack.api.spring.group.usergroups

/**
 * Spring based implementation of [UsergroupsMethodGroup.update]
 */
import io.hndrs.slack.api.contract.jackson.group.usergroups.ErrorUpdateResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.SuccessfulUpdateResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.UpdateResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.usergroups.UsergroupsUpdateMethod
import io.hndrs.slack.api.group.users.UsersMethodGroup
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * Spring based implementation of [UsersMethodGroup.conversations]
 */
@Suppress("UNCHECKED_CAST")
class SpringUsergroupsUpdateMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()
) : UsergroupsUpdateMethod() {

    override fun request(): ApiCallResult<SuccessfulUpdateResponse, ErrorUpdateResponse> {
        val response = SlackRequestBuilder<UpdateResponse>(authToken, restTemplate)
            .toMethod("usergroups.update")
            .returnAsType(UpdateResponse::class.java)
            .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulUpdateResponse -> {
                val responseEntity = response.body as SuccessfulUpdateResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorUpdateResponse -> {
                val responseEntity = response.body as ErrorUpdateResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
