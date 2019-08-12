package com.kreait.slack.api.spring.group.dialog


import com.kreait.slack.api.contract.jackson.group.dialog.ErrorOpenDialogResponse
import com.kreait.slack.api.contract.jackson.group.dialog.SlackOpenDialogResponse
import com.kreait.slack.api.contract.jackson.group.dialog.SuccessfulOpenDialogResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.dialog.DialogOpenMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultDialogOpenMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : DialogOpenMethod() {

    override fun request(): ApiCallResult<SuccessfulOpenDialogResponse, ErrorOpenDialogResponse> {
        val response = SlackRequestBuilder<SlackOpenDialogResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("dialog.open")
                .returnAsType(SlackOpenDialogResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulOpenDialogResponse -> {
                val responseEntity = response.body as SuccessfulOpenDialogResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorOpenDialogResponse -> {
                val responseEntity = response.body as ErrorOpenDialogResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
