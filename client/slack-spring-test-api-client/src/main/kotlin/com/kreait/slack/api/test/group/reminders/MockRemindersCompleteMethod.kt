package com.kreait.slack.api.test.group.reminders

import com.kreait.slack.api.contract.jackson.group.reminders.ErrorRemindersCompleteResponse
import com.kreait.slack.api.contract.jackson.group.reminders.RemindersCompleteRequest
import com.kreait.slack.api.contract.jackson.group.reminders.SuccessfulRemindersCompleteResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.reminders.RemindersCompleteMethod
import com.kreait.slack.api.group.reminders.RemindersMethodGroup
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [RemindersMethodGroup.complete]
 */
class MockRemindersCompleteMethod : RemindersCompleteMethod(),
    MockMethod<SuccessfulRemindersCompleteResponse, ErrorRemindersCompleteResponse, RemindersCompleteRequest> {

    override var successResponse: SuccessfulRemindersCompleteResponse? = null
    override var failureResponse: ErrorRemindersCompleteResponse? = null

    override fun request(): ApiCallResult<SuccessfulRemindersCompleteResponse, ErrorRemindersCompleteResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params() = params
}
