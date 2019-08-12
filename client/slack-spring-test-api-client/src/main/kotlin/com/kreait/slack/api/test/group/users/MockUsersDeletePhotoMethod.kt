package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersDeletePhotoResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersDeletePhotoResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UsersDeletePhotoMethod
import com.kreait.slack.api.test.MockMethod

class MockUsersDeletePhotoMethod : UsersDeletePhotoMethod(), MockMethod<SuccessfulUsersDeletePhotoResponse, ErrorUsersDeletePhotoResponse, Unit> {
    override fun params(): Unit {
        return params
    }

    override var successResponse: SuccessfulUsersDeletePhotoResponse? = null
    override var failureResponse: ErrorUsersDeletePhotoResponse? = null

    override fun request(): ApiCallResult<SuccessfulUsersDeletePhotoResponse, ErrorUsersDeletePhotoResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
