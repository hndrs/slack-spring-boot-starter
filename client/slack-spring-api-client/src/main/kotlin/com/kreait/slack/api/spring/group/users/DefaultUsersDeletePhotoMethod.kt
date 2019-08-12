package com.kreait.slack.api.spring.group.users


import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UsersDeletePhotoMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersDeletePhotoResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersDeletePhotoResponse
import com.kreait.slack.api.contract.jackson.group.users.UsersDeletePhotoResponse
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultUsersDeletePhotoMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UsersDeletePhotoMethod() {

    override fun request(): ApiCallResult<SuccessfulUsersDeletePhotoResponse, ErrorUsersDeletePhotoResponse> {
        val response = SlackRequestBuilder<UsersDeletePhotoResponse>(authToken, restTemplate)
                .toMethod("users.deletePhoto")
                .returnAsType(UsersDeletePhotoResponse::class.java)
                .postUrlEncoded(mapOf())

        return when (response.body!!) {
            is SuccessfulUsersDeletePhotoResponse -> {
                val responseEntity = response.body as SuccessfulUsersDeletePhotoResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorUsersDeletePhotoResponse -> {
                val responseEntity = response.body as ErrorUsersDeletePhotoResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
