package io.olaph.slack.client.test.group.usergroups

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.usergroups.UsergroupsEnableMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.usergroups.ErrorUsergroupsEnableResponse
import io.olaph.slack.dto.jackson.group.usergroups.SlackUsergroupsEnableRequest
import io.olaph.slack.dto.jackson.group.usergroups.SuccessfulUsergroupsEnableResponse

class MockUsergroupsEnableMethod : UsergroupsEnableMethod(), MockMethod<SuccessfulUsergroupsEnableResponse, ErrorUsergroupsEnableResponse, SlackUsergroupsEnableRequest> {

    override fun params(): SlackUsergroupsEnableRequest = params

    override var successResponse: SuccessfulUsergroupsEnableResponse? = null
    override var failureResponse: ErrorUsergroupsEnableResponse? = null

    override fun request(): ApiCallResult<SuccessfulUsergroupsEnableResponse, ErrorUsergroupsEnableResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}