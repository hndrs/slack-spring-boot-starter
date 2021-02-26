package io.hndrs.slack.api.test.group.team

import io.hndrs.slack.api.contract.jackson.group.team.ErrorProfileResponse
import io.hndrs.slack.api.contract.jackson.group.team.ProfileRequest
import io.hndrs.slack.api.contract.jackson.group.team.SuccessfulProfileResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.team.TeamGetProfileMethod
import io.hndrs.slack.api.group.team.TeamMethodGroup
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [TeamMethodGroup.getProfile]
 */
class MockTeamGetProfileMethod : io.hndrs.slack.api.group.team.TeamGetProfileMethod(),
    MockMethod<SuccessfulProfileResponse, ErrorProfileResponse, ProfileRequest> {
    
    override var successResponse: SuccessfulProfileResponse? = null
    override var failureResponse: ErrorProfileResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulProfileResponse, ErrorProfileResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): ProfileRequest = params
}
