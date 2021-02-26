package io.hndrs.slack.api.spring.group.users


/**
 * https://api.slack.com/methods/users.setPhoto
 */
import io.hndrs.slack.api.contract.jackson.group.users.ErrorSetPhotoResponse
import io.hndrs.slack.api.contract.jackson.group.users.SetPhotoResponse
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulSetPhotoResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.users.UsersMethodGroup
import io.hndrs.slack.api.group.users.UsersSetPhotoMethod
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.core.io.FileSystemResource
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate
import java.io.File


/**
 * Spring based implementation of [UsersMethodGroup.setPhoto]
 */
@Suppress("UNCHECKED_CAST")
class SpringUsersSetPhotoMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate()
) : io.hndrs.slack.api.group.users.UsersSetPhotoMethod() {

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulSetPhotoResponse, ErrorSetPhotoResponse> {
        val response = SlackRequestBuilder<SetPhotoResponse>(authToken, restTemplate)
            .with(LinkedMultiValueMap(convertParams()))
            .toMethod("users.setPhoto")
            .returnAsType(SetPhotoResponse::class.java)
            .postMultipartFormdata()

        return when (response.body!!) {
            is SuccessfulSetPhotoResponse -> {
                val responseEntity = response.body as SuccessfulSetPhotoResponse
                this.onSuccess?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(success = responseEntity)
            }
            is ErrorSetPhotoResponse -> {
                val responseEntity = response.body as ErrorSetPhotoResponse
                this.onFailure?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(failure = responseEntity)
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
