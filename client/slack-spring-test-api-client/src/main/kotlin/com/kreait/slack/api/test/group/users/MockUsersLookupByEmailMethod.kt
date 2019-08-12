package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UsersLookupByEmailMethod
import com.kreait.slack.api.test.MockMethod
import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersLookupByEmailResponse
import com.kreait.slack.api.contract.jackson.group.users.SlackUsersLookupByEmailRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersLookupByEmailResponse

class MockUsersLookupByEmailMethod : UsersLookupByEmailMethod(), MockMethod<SuccessfulUsersLookupByEmailResponse, ErrorUsersLookupByEmailResponse, SlackUsersLookupByEmailRequest> {

    override fun params(): SlackUsersLookupByEmailRequest = params

    override var successResponse: SuccessfulUsersLookupByEmailResponse? = null
    override var failureResponse: ErrorUsersLookupByEmailResponse? = null

    override fun request(): ApiCallResult<SuccessfulUsersLookupByEmailResponse, ErrorUsersLookupByEmailResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(success = this.successResponse, failure = this.failureResponse)
    }
}
