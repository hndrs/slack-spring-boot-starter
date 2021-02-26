package io.hndrs.slack.api.test.group.users

import io.hndrs.slack.api.contract.jackson.group.users.ErrorSetProfileResponse
import io.hndrs.slack.api.contract.jackson.group.users.SetProfileRequest
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulSetProfileResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.users.UsersMethodGroup
import io.hndrs.slack.api.group.users.UsersSetProfileMethod
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [UsersMethodGroup.setProfile]
 */
class MockUsersSetProfileMethod : io.hndrs.slack.api.group.users.UsersSetProfileMethod(),
    MockMethod<SuccessfulSetProfileResponse, ErrorSetProfileResponse, SetProfileRequest> {
    
    override var successResponse: SuccessfulSetProfileResponse? = null
    override var failureResponse: ErrorSetProfileResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulSetProfileResponse, ErrorSetProfileResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): SetProfileRequest = params
}
