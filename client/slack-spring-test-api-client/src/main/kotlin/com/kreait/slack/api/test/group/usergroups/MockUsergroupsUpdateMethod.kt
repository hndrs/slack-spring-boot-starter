package com.kreait.slack.api.test.group.usergroups

import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorUpdateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulUpdateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.UpdateRequest
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.usergroups.UsergroupsMethodGroup
import com.kreait.slack.api.group.usergroups.UsergroupsUpdateMethod
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [UsergroupsMethodGroup.update]
 */
class MockUsergroupsUpdateMethod : UsergroupsUpdateMethod(), MockMethod<SuccessfulUpdateResponse, ErrorUpdateResponse, UpdateRequest> {

    override fun params(): UpdateRequest = params
    override var successResponse: SuccessfulUpdateResponse? = null
    override var failureResponse: ErrorUpdateResponse? = null

    override fun request(): ApiCallResult<SuccessfulUpdateResponse, ErrorUpdateResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }
        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
