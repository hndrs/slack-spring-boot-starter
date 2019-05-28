package io.olaph.slack.client.test.group.usergroups

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.usergroups.UsergroupsDisableMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.usergroups.ErrorUsergroupsDisableResponse
import io.olaph.slack.dto.jackson.group.usergroups.SlackUsergroupsDisableRequest
import io.olaph.slack.dto.jackson.group.usergroups.SuccessfulUsergroupsDisableResponse

class MockUsergroupsDisableMethod : UsergroupsDisableMethod(), MockMethod<SuccessfulUsergroupsDisableResponse, ErrorUsergroupsDisableResponse, SlackUsergroupsDisableRequest> {

    override fun params(): SlackUsergroupsDisableRequest = params

    override var successResponse: SuccessfulUsergroupsDisableResponse? = null
    override var failureResponse: ErrorUsergroupsDisableResponse? = null

    override fun request(): ApiCallResult<SuccessfulUsergroupsDisableResponse, ErrorUsergroupsDisableResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}