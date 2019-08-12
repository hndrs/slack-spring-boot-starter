package com.kreait.slack.api.group.auth

import com.kreait.slack.api.group.ApiCallMethod
import com.kreait.slack.api.contract.jackson.group.auth.AuthRevokeRequest
import com.kreait.slack.api.contract.jackson.group.auth.ErrorAuthRevokeResponse
import com.kreait.slack.api.contract.jackson.group.auth.SuccessfulAuthRevokeResponse

abstract class AuthRevokeMethod : ApiCallMethod<AuthRevokeMethod, SuccessfulAuthRevokeResponse, ErrorAuthRevokeResponse, AuthRevokeRequest>() {
}
