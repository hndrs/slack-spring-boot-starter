package io.hndrs.slack.api.test.group.reminders

import io.hndrs.slack.api.contract.jackson.group.reminders.ErrorRemindersInfoResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.RemindersInfoRequest
import io.hndrs.slack.api.contract.jackson.group.reminders.SuccessfulRemindersInfoResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.reminders.RemindersInfoMethod
import io.hndrs.slack.api.group.reminders.RemindersMethodGroup
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [RemindersMethodGroup.info]
 */
class MockRemindersInfoMethod : io.hndrs.slack.api.group.reminders.RemindersInfoMethod(),
    MockMethod<SuccessfulRemindersInfoResponse, ErrorRemindersInfoResponse, RemindersInfoRequest> {
    
    override var successResponse: SuccessfulRemindersInfoResponse? = null
    override var failureResponse: ErrorRemindersInfoResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulRemindersInfoResponse, ErrorRemindersInfoResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params() = params
}
