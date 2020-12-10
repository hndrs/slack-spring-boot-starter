package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorListResponse
import com.kreait.slack.api.contract.jackson.group.users.ListRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulListResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UserListMethod
import com.kreait.slack.api.group.users.UsersMethodGroup
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [UsersMethodGroup.list]
 */
class MockUserListMethod : UserListMethod(), MockMethod<SuccessfulListResponse, ErrorListResponse, ListRequest> {
    
    override var successResponse: SuccessfulListResponse? = null
    override var failureResponse: ErrorListResponse? = null

    override fun request(): ApiCallResult<SuccessfulListResponse, ErrorListResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): ListRequest = params
}
