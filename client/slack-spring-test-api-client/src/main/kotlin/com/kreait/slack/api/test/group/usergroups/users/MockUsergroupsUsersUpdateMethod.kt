package com.kreait.slack.api.test.group.usergroups.users

import com.kreait.slack.api.contract.jackson.group.usergroups.users.ErrorUsergroupUsersUpdateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.users.SuccessfulUsergroupUsersUpdateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.users.UsergroupUsersUpdateRequest
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.usergroups.UsergroupsMethodGroup
import com.kreait.slack.api.group.usergroups.users.UsergroupsUsersUpdateMethod
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [UsergroupsMethodGroup.replaceUsers]
 */
class MockUsergroupsUsersUpdateMethod : UsergroupsUsersUpdateMethod(),
    MockMethod<SuccessfulUsergroupUsersUpdateResponse, ErrorUsergroupUsersUpdateResponse, UsergroupUsersUpdateRequest> {
    
    override var successResponse: SuccessfulUsergroupUsersUpdateResponse? = null
    override var failureResponse: ErrorUsergroupUsersUpdateResponse? = null

    override fun request(): ApiCallResult<SuccessfulUsergroupUsersUpdateResponse, ErrorUsergroupUsersUpdateResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): UsergroupUsersUpdateRequest = params
}
