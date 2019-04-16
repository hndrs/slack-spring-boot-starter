package io.olaph.slack.client.spring.group.dialog

import io.olaph.slack.client.UnknownResponseException
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.dialog.DialogOpenMethod
import io.olaph.slack.dto.jackson.group.dialog.ErrorOpenDialogResponse
import io.olaph.slack.dto.jackson.group.dialog.SlackOpenDialogResponse
import io.olaph.slack.dto.jackson.group.dialog.SuccessfulOpenDialogResponse
import org.springframework.web.client.RestTemplate
import org.springframework.http.client.BufferingClientHttpRequestFactory
import org.springframework.http.client.SimpleClientHttpRequestFactory


@Suppress("UNCHECKED_CAST")
class DefaultDialogOpenMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplate(BufferingClientHttpRequestFactory(SimpleClientHttpRequestFactory()))) : DialogOpenMethod() {

    override fun request(): ApiCallResult<SuccessfulOpenDialogResponse, ErrorOpenDialogResponse> {
        val response = SlackRequestBuilder<SlackOpenDialogResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("dialog.open")
                .returnAsType(SlackOpenDialogResponse::class.java)
                .postWithJsonBody()

        return when {
            response.body is SuccessfulOpenDialogResponse -> {
                val responseEntity = response.body as SuccessfulOpenDialogResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            response.body is ErrorOpenDialogResponse -> {
                val responseEntity = response.body as ErrorOpenDialogResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
            else -> {
                throw UnknownResponseException(this::class, response)
            }
        }
    }


}
