package io.olaph.slack.client.test.group.oauth

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.oauth.OauthAccessMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.oauth.ErrorOauthAccessResponse
import io.olaph.slack.dto.jackson.group.oauth.OauthAccessRequest
import io.olaph.slack.dto.jackson.group.oauth.SuccessFullOauthAccessResponse

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
