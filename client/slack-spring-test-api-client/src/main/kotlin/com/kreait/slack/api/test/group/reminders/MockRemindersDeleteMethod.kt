package com.kreait.slack.api.test.group.reminders

import com.kreait.slack.api.contract.jackson.group.reminders.ErrorRemindersDeleteResponse
import com.kreait.slack.api.contract.jackson.group.reminders.RemindersDeleteRequest
import com.kreait.slack.api.contract.jackson.group.reminders.SuccessfulRemindersDeleteResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.reminders.RemindersDeleteMethod
import com.kreait.slack.api.test.MockMethod

/**
 * Mock implementation of [RemindersDeleteMethod]
 */
class MockRemindersDeleteMethod : RemindersDeleteMethod(), MockMethod<SuccessfulRemindersDeleteResponse, ErrorRemindersDeleteResponse, RemindersDeleteRequest> {

    override fun params() = params

    override var successResponse: SuccessfulRemindersDeleteResponse? = null
    override var failureResponse: ErrorRemindersDeleteResponse? = null

    override fun request(): ApiCallResult<SuccessfulRemindersDeleteResponse, ErrorRemindersDeleteResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
