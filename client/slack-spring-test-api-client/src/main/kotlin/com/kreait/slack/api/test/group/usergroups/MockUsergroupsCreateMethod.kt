package com.kreait.slack.api.test.group.usergroups

import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorCreateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.CreateRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulCreateResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.usergroups.UsergroupsCreateMethod
import com.kreait.slack.api.test.MockMethod

/**
 * Mock implementation of [UsergroupsCreateMethod]
 */
class MockUsergroupsCreateMethod : UsergroupsCreateMethod(), MockMethod<SuccessfulCreateResponse, ErrorCreateResponse, CreateRequest> {

    override fun params(): CreateRequest = params

    override var successResponse: SuccessfulCreateResponse? = null
    override var failureResponse: ErrorCreateResponse? = null

    override fun request(): ApiCallResult<SuccessfulCreateResponse, ErrorCreateResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
