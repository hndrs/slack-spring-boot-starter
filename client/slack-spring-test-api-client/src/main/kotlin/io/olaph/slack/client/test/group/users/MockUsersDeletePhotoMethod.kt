package io.olaph.slack.client.test.group.users

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.users.UsersDeletePhotoMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.users.ErrorUsersDeletePhotoResponse
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersDeletePhotoResponse

class MockUsersDeletePhotoMethod : UsersDeletePhotoMethod(), MockMethod<SuccessfulUsersDeletePhotoResponse, ErrorUsersDeletePhotoResponse, Unit> {
    override fun params(): Unit {
        return params
    }

    override var successResponse: SuccessfulUsersDeletePhotoResponse? = null
    override var failureResponse: ErrorUsersDeletePhotoResponse? = null

    override fun request(): ApiCallResult<SuccessfulUsersDeletePhotoResponse, ErrorUsersDeletePhotoResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}