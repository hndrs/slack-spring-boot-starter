package com.kreait.slack.api.test.group.team

import com.kreait.slack.api.contract.jackson.group.team.ErrorTeamGetProfileResponse
import com.kreait.slack.api.contract.jackson.group.team.SuccessfulTeamGetProfileResponse
import com.kreait.slack.api.contract.jackson.group.team.TeamGetProfileRequest
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.team.TeamGetProfileMethod
import com.kreait.slack.api.test.MockMethod

class MockTeamGetProfileMethod : TeamGetProfileMethod(), MockMethod<SuccessfulTeamGetProfileResponse, ErrorTeamGetProfileResponse, TeamGetProfileRequest> {
    override fun params(): TeamGetProfileRequest {
        return params
    }

    override var successResponse: SuccessfulTeamGetProfileResponse? = null
    override var failureResponse: ErrorTeamGetProfileResponse? = null

    override fun request(): ApiCallResult<SuccessfulTeamGetProfileResponse, ErrorTeamGetProfileResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
