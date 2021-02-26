package io.hndrs.slack.api.spring.group.usergroups.users


/**
 * Spring based implementation of [UsergroupsMethodGroup.usersUpdate]
 */
import io.hndrs.slack.api.contract.jackson.group.usergroups.users.ErrorUsergroupUsersUpdateResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.users.SuccessfulUsergroupUsersUpdateResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.users.UsergroupsUsersUpdateResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.usergroups.users.UsergroupsUsersUpdateMethod
import io.hndrs.slack.api.group.users.UsersMethodGroup
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * Spring based implementation of [UsersMethodGroup.conversations]
 */
@Suppress("UNCHECKED_CAST")
class SpringUsergroupsUsersUpdateMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate()
) : io.hndrs.slack.api.group.usergroups.users.UsergroupsUsersUpdateMethod() {

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulUsergroupUsersUpdateResponse, ErrorUsergroupUsersUpdateResponse> {
        val response = SlackRequestBuilder<UsergroupsUsersUpdateResponse>(authToken, restTemplate)
            .toMethod("usergroups.users.update")
            .returnAsType(UsergroupsUsersUpdateResponse::class.java)
            .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulUsergroupUsersUpdateResponse -> {
                val responseEntity = response.body as SuccessfulUsergroupUsersUpdateResponse
                this.onSuccess?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(success = responseEntity)
            }
            is ErrorUsergroupUsersUpdateResponse -> {
                val responseEntity = response.body as ErrorUsergroupUsersUpdateResponse
                this.onFailure?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(failure = responseEntity)
            }
        }
    }
}
