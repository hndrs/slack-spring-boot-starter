package io.hndrs.slack.api.test.group.users

import io.hndrs.slack.api.contract.jackson.group.users.ErrorInfoResponse
import io.hndrs.slack.api.contract.jackson.group.users.InfoRequest
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulInfoResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.users.UsersInfoMethod
import io.hndrs.slack.api.group.users.UsersMethodGroup
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [UsersMethodGroup.info]
 */
class MockUsersInfoMethod : io.hndrs.slack.api.group.users.UsersInfoMethod(), MockMethod<SuccessfulInfoResponse, ErrorInfoResponse, InfoRequest> {

    override var successResponse: SuccessfulInfoResponse? = null
    override var failureResponse: ErrorInfoResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulInfoResponse, ErrorInfoResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): InfoRequest = params
}
