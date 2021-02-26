package io.hndrs.slack.api.test.group.reminders

import io.hndrs.slack.api.contract.jackson.group.reminders.ErrorRemindersDeleteResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.RemindersDeleteRequest
import io.hndrs.slack.api.contract.jackson.group.reminders.SuccessfulRemindersDeleteResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.reminders.RemindersDeleteMethod
import io.hndrs.slack.api.group.reminders.RemindersMethodGroup
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [RemindersMethodGroup.delete]
 */
class MockRemindersDeleteMethod : io.hndrs.slack.api.group.reminders.RemindersDeleteMethod(), MockMethod<SuccessfulRemindersDeleteResponse, ErrorRemindersDeleteResponse, RemindersDeleteRequest> {

    override var successResponse: SuccessfulRemindersDeleteResponse? = null
    override var failureResponse: ErrorRemindersDeleteResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulRemindersDeleteResponse, ErrorRemindersDeleteResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params() = params
}
