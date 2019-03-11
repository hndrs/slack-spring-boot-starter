package io.olaph.slack.client.implementation.group.dialog

import io.olaph.slack.client.UnknownResponseException
import io.olaph.slack.client.implementation.group.SlackRequestBuilder
import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.dialog.DialogOpenMethod
import io.olaph.slack.dto.jackson.group.dialog.ErrorOpenDialogResponse
import io.olaph.slack.dto.jackson.group.dialog.SlackOpenDialogResponse
import io.olaph.slack.dto.jackson.group.dialog.SuccessfulOpenDialogResponse

@Suppress("UNCHECKED_CAST")
class DefaultDialogOpenMethod(private val authToken: String) : DialogOpenMethod() {

    override fun request(): ApiCallResult<SuccessfulOpenDialogResponse, ErrorOpenDialogResponse> {
        val response = SlackRequestBuilder<SlackOpenDialogResponse>(authToken)
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
