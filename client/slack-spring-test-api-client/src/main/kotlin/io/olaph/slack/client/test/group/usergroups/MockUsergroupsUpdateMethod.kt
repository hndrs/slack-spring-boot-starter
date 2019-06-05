package io.olaph.slack.client.test.group.usergroups

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.usergroups.UsergroupsUpdateMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.usergroups.ErrorUsergroupsUpdateResponse
import io.olaph.slack.dto.jackson.group.usergroups.SlackUsergroupsUpdateRequest
import io.olaph.slack.dto.jackson.group.usergroups.SuccessfulUsergroupsUpdateResponse

class MockUsergroupsUpdateMethod : UsergroupsUpdateMethod(), MockMethod<SuccessfulUsergroupsUpdateResponse, ErrorUsergroupsUpdateResponse, SlackUsergroupsUpdateRequest> {

    override fun params(): SlackUsergroupsUpdateRequest = params
    override var successResponse: SuccessfulUsergroupsUpdateResponse? = null
    override var failureResponse: ErrorUsergroupsUpdateResponse? = null

    override fun request(): ApiCallResult<SuccessfulUsergroupsUpdateResponse, ErrorUsergroupsUpdateResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }
        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}