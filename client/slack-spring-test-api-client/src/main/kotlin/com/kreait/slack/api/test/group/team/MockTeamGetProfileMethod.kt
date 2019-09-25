package com.kreait.slack.api.test.group.team

import com.kreait.slack.api.contract.jackson.group.team.ErrorProfileResponse
import com.kreait.slack.api.contract.jackson.group.team.ProfileRequest
import com.kreait.slack.api.contract.jackson.group.team.SuccessfulProfileResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.team.TeamGetProfileMethod
import com.kreait.slack.api.group.team.TeamMethodGroup
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [TeamMethodGroup.getProfile]
 */
class MockTeamGetProfileMethod : TeamGetProfileMethod(), MockMethod<SuccessfulProfileResponse, ErrorProfileResponse, ProfileRequest> {

    override fun params(): ProfileRequest = params

    override var successResponse: SuccessfulProfileResponse? = null
    override var failureResponse: ErrorProfileResponse? = null

    override fun request(): ApiCallResult<SuccessfulProfileResponse, ErrorProfileResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
