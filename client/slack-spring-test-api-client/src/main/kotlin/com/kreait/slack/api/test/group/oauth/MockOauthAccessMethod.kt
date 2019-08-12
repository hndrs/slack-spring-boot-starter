package com.kreait.slack.api.test.group.oauth

import com.kreait.slack.api.contract.jackson.group.oauth.ErrorOauthAccessResponse
import com.kreait.slack.api.contract.jackson.group.oauth.OauthAccessRequest
import com.kreait.slack.api.contract.jackson.group.oauth.SuccessFullOauthAccessResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.oauth.OauthAccessMethod
import com.kreait.slack.api.test.MockMethod

class MockOauthAccessMethod : OauthAccessMethod(), MockMethod<SuccessFullOauthAccessResponse, ErrorOauthAccessResponse, OauthAccessRequest> {

    override var successResponse: SuccessFullOauthAccessResponse? = null

    override var failureResponse: ErrorOauthAccessResponse? = null

    override fun params(): OauthAccessRequest {
        return params
    }

    override fun request(): ApiCallResult<SuccessFullOauthAccessResponse, ErrorOauthAccessResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
