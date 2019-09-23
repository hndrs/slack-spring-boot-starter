package com.kreait.slack.api.spring.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorSetPhotoResponse
import com.kreait.slack.api.contract.jackson.group.users.SetPhotoResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulSetPhotoResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UsersSetPhotoMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.core.io.FileSystemResource
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate
import java.io.File


/**
 * https://api.slack.com/methods/users.setPhoto
 */
@Suppress("UNCHECKED_CAST")
class SpringUsersSetPhotoMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UsersSetPhotoMethod() {

    override fun request(): ApiCallResult<SuccessfulSetPhotoResponse, ErrorSetPhotoResponse> {
        val response = SlackRequestBuilder<SetPhotoResponse>(authToken, restTemplate)
                .with(LinkedMultiValueMap(convertParams()))
                .toMethod("users.setPhoto")
                .returnAsType(SetPhotoResponse::class.java)
                .postMultipartFormdata()

        return when (response.body!!) {
            is SuccessfulSetPhotoResponse -> {
                val responseEntity = response.body as SuccessfulSetPhotoResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorSetPhotoResponse -> {
                val responseEntity = response.body as ErrorSetPhotoResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }

    /**
     * Converts the file list to FileSystemResource-List to handle resources correctly
     */
    private fun convertParams(): Map<String, List<Any>> {
        return this.params.toMap().mapValues {
            if (it.value.all { value -> value is File }) {
                val sysResList = it.value.map {
                    FileSystemResource(it as File)
                }
                sysResList
            } else it.value
        }
    }
}
