package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersSetPhotoResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersSetPhotoResponse
import com.kreait.slack.api.contract.jackson.group.users.UsersSetPhotoRequest
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UsersSetPhotoMethod
import com.kreait.slack.api.test.MockMethod

class MockUsersSetPhotoMethod : UsersSetPhotoMethod(), MockMethod<SuccessfulUsersSetPhotoResponse, ErrorUsersSetPhotoResponse, UsersSetPhotoRequest> {

    override fun params() = params

    override var successResponse: SuccessfulUsersSetPhotoResponse? = null
    override var failureResponse: ErrorUsersSetPhotoResponse? = null

    override fun request(): ApiCallResult<SuccessfulUsersSetPhotoResponse, ErrorUsersSetPhotoResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
