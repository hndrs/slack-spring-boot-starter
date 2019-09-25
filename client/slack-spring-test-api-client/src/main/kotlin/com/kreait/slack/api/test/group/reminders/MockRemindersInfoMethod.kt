package com.kreait.slack.api.test.group.reminders

import com.kreait.slack.api.contract.jackson.group.reminders.ErrorRemindersInfoResponse
import com.kreait.slack.api.contract.jackson.group.reminders.RemindersInfoRequest
import com.kreait.slack.api.contract.jackson.group.reminders.SuccessfulRemindersInfoResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.reminders.RemindersInfoMethod
import com.kreait.slack.api.group.reminders.RemindersMethodGroup
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [RemindersMethodGroup.info]
 */
class MockRemindersInfoMethod : RemindersInfoMethod(), MockMethod<SuccessfulRemindersInfoResponse, ErrorRemindersInfoResponse, RemindersInfoRequest> {

    override fun params() = params

    override var successResponse: SuccessfulRemindersInfoResponse? = null
    override var failureResponse: ErrorRemindersInfoResponse? = null

    override fun request(): ApiCallResult<SuccessfulRemindersInfoResponse, ErrorRemindersInfoResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
