package com.kreait.slack.api.group.auth

import com.kreait.slack.api.contract.jackson.group.auth.AuthRevokeRequest
import com.kreait.slack.api.contract.jackson.group.auth.ErrorAuthRevokeResponse
import com.kreait.slack.api.contract.jackson.group.auth.SuccessfulAuthRevokeResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class AuthRevokeMethod : ApiCallMethod<AuthRevokeMethod, SuccessfulAuthRevokeResponse, ErrorAuthRevokeResponse, AuthRevokeRequest>() {
}
