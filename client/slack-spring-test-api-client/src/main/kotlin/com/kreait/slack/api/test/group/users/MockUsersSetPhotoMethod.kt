package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorSetPhotoResponse
import com.kreait.slack.api.contract.jackson.group.users.SetPhotoRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulSetPhotoResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UsersMethodGroup
import com.kreait.slack.api.group.users.UsersSetPhotoMethod
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [UsersMethodGroup.setPhoto]
 */
class MockUsersSetPhotoMethod : UsersSetPhotoMethod(), MockMethod<SuccessfulSetPhotoResponse, ErrorSetPhotoResponse, SetPhotoRequest> {

    override fun params() = params

    override var successResponse: SuccessfulSetPhotoResponse? = null
    override var failureResponse: ErrorSetPhotoResponse? = null

    override fun request(): ApiCallResult<SuccessfulSetPhotoResponse, ErrorSetPhotoResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
