package io.hndrs.slack.api.test.group.reminders

import io.hndrs.slack.api.contract.jackson.group.reminders.ErrorRemindersCompleteResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.RemindersCompleteRequest
import io.hndrs.slack.api.contract.jackson.group.reminders.SuccessfulRemindersCompleteResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.reminders.RemindersCompleteMethod
import io.hndrs.slack.api.group.reminders.RemindersMethodGroup
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [RemindersMethodGroup.complete]
 */
class MockRemindersCompleteMethod : io.hndrs.slack.api.group.reminders.RemindersCompleteMethod(),
    MockMethod<SuccessfulRemindersCompleteResponse, ErrorRemindersCompleteResponse, RemindersCompleteRequest> {

    override var successResponse: SuccessfulRemindersCompleteResponse? = null
    override var failureResponse: ErrorRemindersCompleteResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulRemindersCompleteResponse, ErrorRemindersCompleteResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params() = params
}
