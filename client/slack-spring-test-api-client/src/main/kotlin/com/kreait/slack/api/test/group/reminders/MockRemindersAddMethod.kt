package com.kreait.slack.api.test.group.reminders

import com.kreait.slack.api.contract.jackson.group.reminders.ErrorRemindersAddResponse
import com.kreait.slack.api.contract.jackson.group.reminders.RemindersAddRequest
import com.kreait.slack.api.contract.jackson.group.reminders.SuccessfulRemindersAddResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.reminders.RemindersAddMethod
import com.kreait.slack.api.test.MockMethod

class MockRemindersAddMethod : RemindersAddMethod(), MockMethod<SuccessfulRemindersAddResponse, ErrorRemindersAddResponse, RemindersAddRequest> {

    override fun params() = params

    override var successResponse: SuccessfulRemindersAddResponse? = null
    override var failureResponse: ErrorRemindersAddResponse? = null

    override fun request(): ApiCallResult<SuccessfulRemindersAddResponse, ErrorRemindersAddResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
