package io.hndrs.slack.api.test.group.usergroups.users

import io.hndrs.slack.api.contract.jackson.group.usergroups.users.ErrorUsergroupsUsersListResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.users.SuccessfulUsergroupsUsersListResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.users.UsergroupsUsersListRequest
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.usergroups.UsergroupsMethodGroup
import io.hndrs.slack.api.group.usergroups.users.UsergroupsUsersListMethod
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [UsergroupsMethodGroup.listUsers]
 */
class MockUsergroupsUsersListMethod : io.hndrs.slack.api.group.usergroups.users.UsergroupsUsersListMethod(),
    MockMethod<SuccessfulUsergroupsUsersListResponse, ErrorUsergroupsUsersListResponse, UsergroupsUsersListRequest> {
    
    override var successResponse: SuccessfulUsergroupsUsersListResponse? = null
    override var failureResponse: ErrorUsergroupsUsersListResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulUsergroupsUsersListResponse, ErrorUsergroupsUsersListResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }
        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): UsergroupsUsersListRequest = params
}
