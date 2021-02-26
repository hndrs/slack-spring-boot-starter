package io.hndrs.slack.api.test.group.dialog

import io.hndrs.slack.api.contract.jackson.group.dialog.ErrorOpenDialogResponse
import io.hndrs.slack.api.contract.jackson.group.dialog.OpenDialogRequest
import io.hndrs.slack.api.contract.jackson.group.dialog.SuccessfulOpenDialogResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.dialog.DialogMethodGroup
import io.hndrs.slack.api.group.dialog.DialogOpenMethod
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [DialogMethodGroup.open]
 */
class MockDialogOpenMethod : io.hndrs.slack.api.group.dialog.DialogOpenMethod(),
    MockMethod<SuccessfulOpenDialogResponse, ErrorOpenDialogResponse, OpenDialogRequest> {
    
    override var successResponse: SuccessfulOpenDialogResponse? = null
    override var failureResponse: ErrorOpenDialogResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulOpenDialogResponse, ErrorOpenDialogResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): OpenDialogRequest = params
}
