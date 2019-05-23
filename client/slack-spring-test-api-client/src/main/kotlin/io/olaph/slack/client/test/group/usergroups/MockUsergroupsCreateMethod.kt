package io.olaph.slack.client.test.group.usergroups

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.usergroups.UsergroupsCreateMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.usergroups.ErrorUsergroupsCreateResponse
import io.olaph.slack.dto.jackson.group.usergroups.SlackUsergroupsCreateRequest
import io.olaph.slack.dto.jackson.group.usergroups.SuccessfulUsergroupsCreateResponse

class MockUsergroupsCreateMethod : UsergroupsCreateMethod(), MockMethod<SuccessfulUsergroupsCreateResponse, ErrorUsergroupsCreateResponse, SlackUsergroupsCreateRequest> {

    override fun params(): SlackUsergroupsCreateRequest = params

    override var successResponse: SuccessfulUsergroupsCreateResponse? = null
    override var failureResponse: ErrorUsergroupsCreateResponse? = null

    override fun request(): ApiCallResult<SuccessfulUsergroupsCreateResponse, ErrorUsergroupsCreateResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}