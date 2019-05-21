package io.olaph.slack.client.test.group.users

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.users.UsersLookupByEmailMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.users.ErrorUsersLookupByEmailResponse
import io.olaph.slack.dto.jackson.group.users.SlackUsersLookupByEmailRequest
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersLookupByEmailResponse

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