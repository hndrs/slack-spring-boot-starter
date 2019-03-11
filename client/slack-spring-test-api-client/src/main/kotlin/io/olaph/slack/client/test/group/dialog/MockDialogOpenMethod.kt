package io.olaph.slack.client.test.group.dialog

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.dialog.DialogOpenMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.dialog.ErrorOpenDialogResponse
import io.olaph.slack.dto.jackson.group.dialog.SlackOpenDialogRequest
import io.olaph.slack.dto.jackson.group.dialog.SuccessfulOpenDialogResponse

class MockDialogOpenMethod : DialogOpenMethod(), MockMethod<SuccessfulOpenDialogResponse, ErrorOpenDialogResponse, SlackOpenDialogRequest> {

    override fun params(): SlackOpenDialogRequest {
        return params
    }

    override var successResponse: SuccessfulOpenDialogResponse? = null
    override var failureResponse: ErrorOpenDialogResponse? = null

    override fun request(): ApiCallResult<SuccessfulOpenDialogResponse, ErrorOpenDialogResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
