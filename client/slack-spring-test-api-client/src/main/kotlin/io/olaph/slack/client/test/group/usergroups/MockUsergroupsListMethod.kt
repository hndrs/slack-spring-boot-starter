package io.olaph.slack.client.test.group.usergroups

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.usergroups.UsergroupsListMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.usergroups.ErrorUsergroupsListResponse
import io.olaph.slack.dto.jackson.group.usergroups.SlackUsergroupsListRequest
import io.olaph.slack.dto.jackson.group.usergroups.SuccessfulUsergroupsListResponse

class MockUsergroupsListMethod : UsergroupsListMethod(), MockMethod<SuccessfulUsergroupsListResponse, ErrorUsergroupsListResponse, SlackUsergroupsListRequest> {

    override fun params(): SlackUsergroupsListRequest = params

    override var successResponse: SuccessfulUsergroupsListResponse? = null
    override var failureResponse: ErrorUsergroupsListResponse? = null

    override fun request(): ApiCallResult<SuccessfulUsergroupsListResponse, ErrorUsergroupsListResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}