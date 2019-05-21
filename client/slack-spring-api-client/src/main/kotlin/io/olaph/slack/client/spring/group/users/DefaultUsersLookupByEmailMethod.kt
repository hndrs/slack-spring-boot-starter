package io.olaph.slack.client.spring.group.users

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.users.UsersLookupByEmailMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.users.ErrorUsersLookupByEmailResponse
import io.olaph.slack.dto.jackson.group.users.SlackLookupByEmailResponse
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersLookupByEmailResponse
import org.springframework.web.client.RestTemplate

/**
 * https://api.slack.com/methods/users.lookupByEmail
 */
@Suppress("UNCHECKED_CAST")
class DefaultUsersLookupByEmailMethod(private val authToken: String, val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UsersLookupByEmailMethod() {

    override fun request(): ApiCallResult<SuccessfulUsersLookupByEmailResponse, ErrorUsersLookupByEmailResponse> {

        val response = SlackRequestBuilder<SlackLookupByEmailResponse>(authToken, restTemplate)
                .toMethod("users.lookupByEmail")
                .returnAsType(SlackLookupByEmailResponse::class.java)
                .postUrlEncoded(this.params.toRequestMap())

        return when (response.body!!) {

            is SuccessfulUsersLookupByEmailResponse -> {

                val responseEntity = response.body as SuccessfulUsersLookupByEmailResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }

            is ErrorUsersLookupByEmailResponse -> {

                val responseEntity = response.body as ErrorUsersLookupByEmailResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}