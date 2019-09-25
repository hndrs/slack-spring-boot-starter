package com.kreait.slack.api.test.group.respond

import com.kreait.slack.api.contract.jackson.group.respond.RespondMessageRequest
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.respond.RespondMessageMethod
import com.kreait.slack.api.group.respond.RespondMethodGroup
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [RespondMethodGroup.message]
 */
class MockRespondMessageMethod : RespondMessageMethod(), MockMethod<Unit, Unit, RespondMessageRequest> {

    override fun params(): RespondMessageRequest = params

    override var successResponse: Unit? = null
    override var failureResponse: Unit? = null

    override fun request(): ApiCallResult<Unit, Unit> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
