package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorLookupByEmailResponse
import com.kreait.slack.api.contract.jackson.group.users.LookupByEmailRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulLookupByEmailResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UsersLookupByEmailMethod
import com.kreait.slack.api.group.users.UsersMethodGroup
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [UsersMethodGroup.lookupByEmail]
 */
class MockUsersLookupByEmailMethod : UsersLookupByEmailMethod(), MockMethod<SuccessfulLookupByEmailResponse, ErrorLookupByEmailResponse, LookupByEmailRequest> {

    override fun params(): LookupByEmailRequest = params

    override var successResponse: SuccessfulLookupByEmailResponse? = null
    override var failureResponse: ErrorLookupByEmailResponse? = null

    override fun request(): ApiCallResult<SuccessfulLookupByEmailResponse, ErrorLookupByEmailResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(success = this.successResponse, failure = this.failureResponse)
    }
}
