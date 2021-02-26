package io.hndrs.slack.api.test.group.users

import io.hndrs.slack.api.contract.jackson.group.users.ErrorSetPhotoResponse
import io.hndrs.slack.api.contract.jackson.group.users.SetPhotoRequest
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulSetPhotoResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.users.UsersMethodGroup
import io.hndrs.slack.api.group.users.UsersSetPhotoMethod
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [UsersMethodGroup.setPhoto]
 */
class MockUsersSetPhotoMethod : io.hndrs.slack.api.group.users.UsersSetPhotoMethod(),
    MockMethod<SuccessfulSetPhotoResponse, ErrorSetPhotoResponse, SetPhotoRequest> {

    override var successResponse: SuccessfulSetPhotoResponse? = null
    override var failureResponse: ErrorSetPhotoResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulSetPhotoResponse, ErrorSetPhotoResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params() = params
}
