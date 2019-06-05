package io.olaph.slack.client.test.group.usergroups.users

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.usergroups.users.UsergroupsUsersListMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.usergroups.users.ErrorUsergroupsUsersListResponse
import io.olaph.slack.dto.jackson.group.usergroups.users.SlackUsergroupsUsersListRequest
import io.olaph.slack.dto.jackson.group.usergroups.users.SuccessfulUsergroupsUsersListResponse

class MockUsergroupsUsersListMethod : UsergroupsUsersListMethod(), MockMethod<SuccessfulUsergroupsUsersListResponse, ErrorUsergroupsUsersListResponse, SlackUsergroupsUsersListRequest> {

    override fun params(): SlackUsergroupsUsersListRequest = params
    override var successResponse: SuccessfulUsergroupsUsersListResponse? = null
    override var failureResponse: ErrorUsergroupsUsersListResponse? = null

    override fun request(): ApiCallResult<SuccessfulUsergroupsUsersListResponse, ErrorUsergroupsUsersListResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }
        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}