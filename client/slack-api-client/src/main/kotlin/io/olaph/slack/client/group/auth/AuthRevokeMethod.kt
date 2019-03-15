package io.olaph.slack.client.group.auth

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.auth.AuthRevokeRequest
import io.olaph.slack.dto.jackson.group.auth.ErrorAuthRevokeResponse
import io.olaph.slack.dto.jackson.group.auth.SuccessfulAuthRevokeResponse

abstract class AuthRevokeMethod : ApiCallMethod<AuthRevokeMethod, SuccessfulAuthRevokeResponse, ErrorAuthRevokeResponse, AuthRevokeRequest>() {
}
