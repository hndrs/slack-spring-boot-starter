package io.hndrs.slack.api.spring.group.users


import io.hndrs.slack.api.contract.jackson.group.users.ErrorInfoResponse
import io.hndrs.slack.api.contract.jackson.group.users.InfoResponse
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulInfoResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.users.UsersInfoMethod
import io.hndrs.slack.api.group.users.UsersMethodGroup
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * Spring based implementation of [UsersMethodGroup.info]
 */
@Suppress("UNCHECKED_CAST")
class SpringUsersInfoMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate()
) : io.hndrs.slack.api.group.users.UsersInfoMethod() {

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulInfoResponse, ErrorInfoResponse> {
        val response = SlackRequestBuilder<InfoResponse>(authToken, restTemplate)
            .toMethod("users.info")
            .returnAsType(InfoResponse::class.java)
            .postUrlEncoded(this.params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulInfoResponse -> {
                val responseEntity = response.body as SuccessfulInfoResponse
                this.onSuccess?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(success = responseEntity)
            }
            is ErrorInfoResponse -> {
                val responseEntity = response.body as ErrorInfoResponse
                this.onFailure?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(failure = responseEntity)
            }
        }
    }
}
