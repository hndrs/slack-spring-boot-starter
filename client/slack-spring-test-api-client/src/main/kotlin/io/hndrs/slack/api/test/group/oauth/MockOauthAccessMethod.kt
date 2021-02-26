package io.hndrs.slack.api.test.group.oauth

import io.hndrs.slack.api.contract.jackson.group.oauth.AccessRequest
import io.hndrs.slack.api.contract.jackson.group.oauth.ErrorAccessResponse
import io.hndrs.slack.api.contract.jackson.group.oauth.SuccessfullAccessResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.oauth.OauthAccessMethod
import io.hndrs.slack.api.group.oauth.OauthMethodGroup
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [OauthMethodGroup.access]
 */
class MockOauthAccessMethod : io.hndrs.slack.api.group.oauth.OauthAccessMethod(),
    MockMethod<SuccessfullAccessResponse, ErrorAccessResponse, AccessRequest> {

    override var successResponse: SuccessfullAccessResponse? = null
    override var failureResponse: ErrorAccessResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfullAccessResponse, ErrorAccessResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): AccessRequest = params
}
