package io.hndrs.slack.api.test.group.reminders

import io.hndrs.slack.api.contract.jackson.group.reminders.ErrorRemindersAddResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.RemindersAddRequest
import io.hndrs.slack.api.contract.jackson.group.reminders.SuccessfulRemindersAddResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.reminders.RemindersAddMethod
import io.hndrs.slack.api.group.reminders.RemindersMethodGroup
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [RemindersMethodGroup.add]
 */
class MockRemindersAddMethod : io.hndrs.slack.api.group.reminders.RemindersAddMethod(),
    MockMethod<SuccessfulRemindersAddResponse, ErrorRemindersAddResponse, RemindersAddRequest> {

    override var successResponse: SuccessfulRemindersAddResponse? = null
    override var failureResponse: ErrorRemindersAddResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulRemindersAddResponse, ErrorRemindersAddResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params() = params
}
