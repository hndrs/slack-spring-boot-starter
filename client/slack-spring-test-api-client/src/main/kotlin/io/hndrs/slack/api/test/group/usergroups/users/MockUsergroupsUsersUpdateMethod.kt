package io.hndrs.slack.api.test.group.usergroups.users

import io.hndrs.slack.api.contract.jackson.group.usergroups.users.ErrorUsergroupUsersUpdateResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.users.SuccessfulUsergroupUsersUpdateResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.users.UsergroupUsersUpdateRequest
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.usergroups.UsergroupsMethodGroup
import io.hndrs.slack.api.group.usergroups.users.UsergroupsUsersUpdateMethod
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [UsergroupsMethodGroup.replaceUsers]
 */
class MockUsergroupsUsersUpdateMethod : io.hndrs.slack.api.group.usergroups.users.UsergroupsUsersUpdateMethod(),
    MockMethod<SuccessfulUsergroupUsersUpdateResponse, ErrorUsergroupUsersUpdateResponse, UsergroupUsersUpdateRequest> {
    
    override var successResponse: SuccessfulUsergroupUsersUpdateResponse? = null
    override var failureResponse: ErrorUsergroupUsersUpdateResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulUsergroupUsersUpdateResponse, ErrorUsergroupUsersUpdateResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): UsergroupUsersUpdateRequest = params
}
