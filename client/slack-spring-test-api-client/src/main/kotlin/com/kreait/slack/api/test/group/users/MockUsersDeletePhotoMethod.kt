package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorDeletePhotoResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulDeletePhotoResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UsersDeletePhotoMethod
import com.kreait.slack.api.group.users.UsersMethodGroup
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [UsersMethodGroup.deletePhoto]
 */
class MockUsersDeletePhotoMethod : UsersDeletePhotoMethod(),
    MockMethod<SuccessfulDeletePhotoResponse, ErrorDeletePhotoResponse, Unit> {

    override var successResponse: SuccessfulDeletePhotoResponse? = null
    override var failureResponse: ErrorDeletePhotoResponse? = null

    override fun request(): ApiCallResult<SuccessfulDeletePhotoResponse, ErrorDeletePhotoResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params() {
        // This method has no params thus this body is empty
    }
}
