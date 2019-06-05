package io.olaph.slack.client.test.group.usergroups.users

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.usergroups.users.UsergroupsUsersUpdateMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.usergroups.users.ErrorUsergroupUsersUpdateResponse
import io.olaph.slack.dto.jackson.group.usergroups.users.SlackUsergroupUsersUpdateRequest
import io.olaph.slack.dto.jackson.group.usergroups.users.SuccessfulUsergroupUsersUpdateResponse

class MockUsergroupsUsersUpdateMethod : UsergroupsUsersUpdateMethod(), MockMethod<SuccessfulUsergroupUsersUpdateResponse, ErrorUsergroupUsersUpdateResponse, SlackUsergroupUsersUpdateRequest> {

    override fun params(): SlackUsergroupUsersUpdateRequest = params
    override var successResponse: SuccessfulUsergroupUsersUpdateResponse? = null
    override var failureResponse: ErrorUsergroupUsersUpdateResponse? = null

    override fun request(): ApiCallResult<SuccessfulUsergroupUsersUpdateResponse, ErrorUsergroupUsersUpdateResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }
        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}