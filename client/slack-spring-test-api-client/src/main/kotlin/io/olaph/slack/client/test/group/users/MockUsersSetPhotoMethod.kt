package io.olaph.slack.client.test.group.users

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.users.UsersSetPhotoMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.users.ErrorUsersSetPhotoResponse
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersSetPhotoResponse
import io.olaph.slack.dto.jackson.group.users.UsersSetPhotoRequest

class MockUsersSetPhotoMethod : UsersSetPhotoMethod(), MockMethod<SuccessfulUsersSetPhotoResponse, ErrorUsersSetPhotoResponse, UsersSetPhotoRequest> {

    override fun params() = params

    override var successResponse: SuccessfulUsersSetPhotoResponse? = null
    override var failureResponse: ErrorUsersSetPhotoResponse? = null

    override fun request(): ApiCallResult<SuccessfulUsersSetPhotoResponse, ErrorUsersSetPhotoResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}