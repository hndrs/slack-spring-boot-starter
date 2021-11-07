package io.hndrs.slack.api.spring.group.users


import io.hndrs.slack.api.contract.jackson.group.users.DeletePhotoResponse
import io.hndrs.slack.api.contract.jackson.group.users.ErrorDeletePhotoResponse
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulDeletePhotoResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.users.UsersDeletePhotoMethod
import io.hndrs.slack.api.group.users.UsersMethodGroup
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * Spring based implementation of [UsersMethodGroup.deletePhoto]
 */
@Suppress("UNCHECKED_CAST")
class SpringUsersDeletePhotoMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()
) : UsersDeletePhotoMethod() {

    override fun request(): ApiCallResult<SuccessfulDeletePhotoResponse, ErrorDeletePhotoResponse> {
        val response = SlackRequestBuilder<DeletePhotoResponse>(authToken, restTemplate)
            .toMethod("users.deletePhoto")
            .returnAsType(DeletePhotoResponse::class.java)
            .postUrlEncoded(mapOf())

        return when (response.body!!) {
            is SuccessfulDeletePhotoResponse -> {
                val responseEntity = response.body as SuccessfulDeletePhotoResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorDeletePhotoResponse -> {
                val responseEntity = response.body as ErrorDeletePhotoResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
