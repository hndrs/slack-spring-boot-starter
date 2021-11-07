package io.hndrs.slack.api.spring.group.usergroups.users


/**
 * Spring based implementation of [UsergroupsMethodGroup.usersList]
 */
import io.hndrs.slack.api.contract.jackson.group.usergroups.users.ErrorUsergroupsUsersListResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.users.SuccessfulUsergroupsUsersListResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.users.UsergroupsUsersListResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.usergroups.users.UsergroupsUsersListMethod
import io.hndrs.slack.api.group.users.UsersMethodGroup
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * Spring based implementation of [UsersMethodGroup.conversations]
 */
@Suppress("UNCHECKED_CAST")
class SpringUsergroupsUsersListMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()
) : UsergroupsUsersListMethod() {

    override fun request(): ApiCallResult<SuccessfulUsergroupsUsersListResponse, ErrorUsergroupsUsersListResponse> {
        val response = SlackRequestBuilder<UsergroupsUsersListResponse>(authToken, restTemplate)
            .toMethod("usergroups.users.list")
            .returnAsType(UsergroupsUsersListResponse::class.java)
            .postUrlEncoded(this.params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulUsergroupsUsersListResponse -> {
                val responseEntity = response.body as SuccessfulUsergroupsUsersListResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorUsergroupsUsersListResponse -> {
                val responseEntity = response.body as ErrorUsergroupsUsersListResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
