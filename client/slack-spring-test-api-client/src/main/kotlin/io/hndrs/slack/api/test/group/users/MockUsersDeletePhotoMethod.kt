package io.hndrs.slack.api.test.group.users

import io.hndrs.slack.api.contract.jackson.group.users.ErrorDeletePhotoResponse
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulDeletePhotoResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.users.UsersDeletePhotoMethod
import io.hndrs.slack.api.group.users.UsersMethodGroup
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [UsersMethodGroup.deletePhoto]
 */
class MockUsersDeletePhotoMethod : io.hndrs.slack.api.group.users.UsersDeletePhotoMethod(),
    MockMethod<SuccessfulDeletePhotoResponse, ErrorDeletePhotoResponse, Unit> {

    override var successResponse: SuccessfulDeletePhotoResponse? = null
    override var failureResponse: ErrorDeletePhotoResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulDeletePhotoResponse, ErrorDeletePhotoResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params() {
        // This method has no params thus this body is empty
    }
}
