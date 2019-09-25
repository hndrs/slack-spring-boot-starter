package com.kreait.slack.api.test.group.oauth

import com.kreait.slack.api.contract.jackson.group.oauth.AccessRequest
import com.kreait.slack.api.contract.jackson.group.oauth.ErrorAccessResponse
import com.kreait.slack.api.contract.jackson.group.oauth.SuccessfullAccessResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.oauth.OauthAccessMethod
import com.kreait.slack.api.group.oauth.OauthMethodGroup
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [OauthMethodGroup.access]
 */
class MockOauthAccessMethod : OauthAccessMethod(), MockMethod<SuccessfullAccessResponse, ErrorAccessResponse, AccessRequest> {

    override fun params(): AccessRequest = params

    override var successResponse: SuccessfullAccessResponse? = null
    override var failureResponse: ErrorAccessResponse? = null

    override fun request(): ApiCallResult<SuccessfullAccessResponse, ErrorAccessResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
