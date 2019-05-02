package io.olaph.slack.client.spring.group.users


import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.users.UsersDeletePhotoMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.users.ErrorUsersDeletePhotoResponse
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersDeletePhotoResponse
import io.olaph.slack.dto.jackson.group.users.UsersDeletePhotoResponse
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
