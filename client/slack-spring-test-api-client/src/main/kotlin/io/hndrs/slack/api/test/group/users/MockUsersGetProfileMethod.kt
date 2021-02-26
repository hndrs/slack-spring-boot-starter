package io.hndrs.slack.api.test.group.users

import io.hndrs.slack.api.contract.jackson.group.users.ErrorGetProfileResponse
import io.hndrs.slack.api.contract.jackson.group.users.GetProfileRequest
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulGetProfileResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.users.UsersGetProfileMethod
import io.hndrs.slack.api.group.users.UsersMethodGroup
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [UsersMethodGroup.getProfile]
 */
class MockUsersGetProfileMethod : io.hndrs.slack.api.group.users.UsersGetProfileMethod(),
    MockMethod<SuccessfulGetProfileResponse, ErrorGetProfileResponse, GetProfileRequest> {
    
    override var successResponse: SuccessfulGetProfileResponse? = null
    override var failureResponse: ErrorGetProfileResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulGetProfileResponse, ErrorGetProfileResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): GetProfileRequest = params
}
