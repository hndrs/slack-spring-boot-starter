package io.hndrs.slack.api.spring.group.usergroups

/**
 * Spring based implementation of [UsergroupsMethodGroup.create]
 */
import io.hndrs.slack.api.contract.jackson.group.usergroups.CreateResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.ErrorCreateResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.SuccessfulCreateResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.usergroups.UsergroupsCreateMethod
import io.hndrs.slack.api.group.users.UsersMethodGroup
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * Spring based implementation of [UsersMethodGroup.conversations]
 */
@Suppress("UNCHECKED_CAST")
class SpringUsergroupsCreateMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate()
) : io.hndrs.slack.api.group.usergroups.UsergroupsCreateMethod() {

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulCreateResponse, ErrorCreateResponse> {
        val response = SlackRequestBuilder<CreateResponse>(authToken, restTemplate)
            .toMethod("usergroups.create")
            .returnAsType(CreateResponse::class.java)
            .postWithJsonBody()

        return when (response.body!!) {

            is SuccessfulCreateResponse -> {

                val responseEntity = response.body as SuccessfulCreateResponse
                this.onSuccess?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(success = responseEntity)
            }

            is ErrorCreateResponse -> {

                val responseEntity = response.body as ErrorCreateResponse
                this.onFailure?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(failure = responseEntity)
            }
        }
    }
}
