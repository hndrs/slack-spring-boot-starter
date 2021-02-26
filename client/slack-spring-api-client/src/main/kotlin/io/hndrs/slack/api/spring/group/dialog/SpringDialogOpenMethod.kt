package io.hndrs.slack.api.spring.group.dialog


import io.hndrs.slack.api.contract.jackson.group.dialog.ErrorOpenDialogResponse
import io.hndrs.slack.api.contract.jackson.group.dialog.OpenDialogResponse
import io.hndrs.slack.api.contract.jackson.group.dialog.SuccessfulOpenDialogResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.dialog.DialogMethodGroup
import io.hndrs.slack.api.group.dialog.DialogOpenMethod
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [DialogMethodGroup.open]
 */
@Suppress("UNCHECKED_CAST")
class SpringDialogOpenMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate()
) : io.hndrs.slack.api.group.dialog.DialogOpenMethod() {

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulOpenDialogResponse, ErrorOpenDialogResponse> {
        val response = SlackRequestBuilder<OpenDialogResponse>(authToken, restTemplate)
            .with(this.params)
            .toMethod("dialog.open")
            .returnAsType(OpenDialogResponse::class.java)
            .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulOpenDialogResponse -> {
                val responseEntity = response.body as SuccessfulOpenDialogResponse
                this.onSuccess?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(success = responseEntity)
            }
            is ErrorOpenDialogResponse -> {
                val responseEntity = response.body as ErrorOpenDialogResponse
                this.onFailure?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(failure = responseEntity)
            }
        }
    }
}
