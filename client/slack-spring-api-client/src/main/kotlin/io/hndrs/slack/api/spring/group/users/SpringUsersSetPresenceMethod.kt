package io.hndrs.slack.api.spring.group.users

/**
 * https://api.slack.com/methods/users.setPresence
 */
import io.hndrs.slack.api.contract.jackson.group.users.ErrorSetPresenceResponse
import io.hndrs.slack.api.contract.jackson.group.users.SetPresenceResponse
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulSetPresenceResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.users.UsersMethodGroup
import io.hndrs.slack.api.group.users.UsersSetPresenceMethod
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * Spring based implementation of [UsersMethodGroup.setPresence]
 */
@Suppress("UNCHECKED_CAST")
class SpringUsersSetPresenceMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate()
) : io.hndrs.slack.api.group.users.UsersSetPresenceMethod() {

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulSetPresenceResponse, ErrorSetPresenceResponse> {
        val response = SlackRequestBuilder<SetPresenceResponse>(authToken, restTemplate)
            .with(this.params)
            .toMethod("users.setPresence")
            .returnAsType(SetPresenceResponse::class.java)
            .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulSetPresenceResponse -> {
                val responseEntity = response.body as SuccessfulSetPresenceResponse
                this.onSuccess?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(success = responseEntity)
            }
            is ErrorSetPresenceResponse -> {
                val responseEntity = response.body as ErrorSetPresenceResponse
                this.onFailure?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(failure = responseEntity)
            }
        }
    }
}
