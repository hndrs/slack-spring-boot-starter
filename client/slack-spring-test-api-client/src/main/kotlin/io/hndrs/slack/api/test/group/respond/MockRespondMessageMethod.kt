package io.hndrs.slack.api.test.group.respond

import io.hndrs.slack.api.contract.jackson.group.respond.RespondMessageRequest
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.respond.RespondMessageMethod
import io.hndrs.slack.api.group.respond.RespondMethodGroup
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [RespondMethodGroup.message]
 */
class MockRespondMessageMethod : io.hndrs.slack.api.group.respond.RespondMessageMethod(), MockMethod<Unit, Unit, RespondMessageRequest> {
    
    override var successResponse: Unit? = null
    override var failureResponse: Unit? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<Unit, Unit> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): RespondMessageRequest = params
}
