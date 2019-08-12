package com.kreait.slack.api.spring.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersSetPhotoResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersSetPhotoResponse
import com.kreait.slack.api.contract.jackson.group.users.UsersSetPhotoResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UsersSetPhotoMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * https://api.slack.com/methods/users.setPhoto
 */
@Suppress("UNCHECKED_CAST")
class DefaultUsersSetPhotoMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UsersSetPhotoMethod() {

    override fun request(): ApiCallResult<SuccessfulUsersSetPhotoResponse, ErrorUsersSetPhotoResponse> {

        val response = SlackRequestBuilder<UsersSetPhotoResponse>(authToken, restTemplate)
                .with(this.params.toMultiValueMap())
                .toMethod("users.setPhoto")
                .returnAsType(UsersSetPhotoResponse::class.java)
                .postMultipartFormdata()

        return when (response.body!!) {
            is SuccessfulUsersSetPhotoResponse -> {
                val responseEntity = response.body as SuccessfulUsersSetPhotoResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorUsersSetPhotoResponse -> {
                val responseEntity = response.body as ErrorUsersSetPhotoResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
