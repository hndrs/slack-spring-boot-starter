package io.hndrs.slack.api.test.group.reminders

import io.hndrs.slack.api.contract.jackson.group.reminders.ErrorRemindersListResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.SuccessfulRemindersListResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.reminders.RemindersListMethod
import io.hndrs.slack.api.group.reminders.RemindersMethodGroup
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [RemindersMethodGroup.list]
 */
class MockRemindersListMethod : io.hndrs.slack.api.group.reminders.RemindersListMethod(),
    MockMethod<SuccessfulRemindersListResponse, ErrorRemindersListResponse, Unit> {

    override var successResponse: SuccessfulRemindersListResponse? = null
    override var failureResponse: ErrorRemindersListResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulRemindersListResponse, ErrorRemindersListResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params() = params
}
