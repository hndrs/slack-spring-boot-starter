package io.olaph.slack.client.spring.group.users

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.users.UsersSetPhotoMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.users.ErrorUsersSetPhotoResponse
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersSetPhotoResponse
import io.olaph.slack.dto.jackson.group.users.UsersSetPhotoResponse
import org.springframework.web.client.RestTemplate


/**
 * https://api.slack.com/methods/users.setPhoto
 */
@Suppress("UNCHECKED_CAST")
class DefaultUsersSetPhotoMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UsersSetPhotoMethod() {

    override fun request(): ApiCallResult<SuccessfulUsersSetPhotoResponse, ErrorUsersSetPhotoResponse> {
        val response = SlackRequestBuilder<UsersSetPhotoResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("users.setPhoto")
                .returnAsType(UsersSetPhotoResponse::class.java)
                .postMultipartFormdata(mapOf())

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
