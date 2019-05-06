package io.olaph.slack.client.test.group.team

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.team.TeamGetProfileMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.team.ErrorTeamGetProfileResponse
import io.olaph.slack.dto.jackson.group.team.SuccessfulTeamGetProfileResponse
import io.olaph.slack.dto.jackson.group.team.TeamGetProfileRequest

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