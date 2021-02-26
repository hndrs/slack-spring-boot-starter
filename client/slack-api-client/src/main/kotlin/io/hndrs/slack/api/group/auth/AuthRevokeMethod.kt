package io.hndrs.slack.api.group.auth

import io.hndrs.slack.api.contract.jackson.group.auth.AuthRevokeRequest
import io.hndrs.slack.api.contract.jackson.group.auth.ErrorAuthRevokeResponse
import io.hndrs.slack.api.contract.jackson.group.auth.SuccessfulAuthRevokeResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Revokes a token.
 * https://api.slack.com/methods/auth.revoke
 */
abstract class AuthRevokeMethod :
    io.hndrs.slack.api.group.ApiCallMethod<io.hndrs.slack.api.group.auth.AuthRevokeMethod, SuccessfulAuthRevokeResponse, ErrorAuthRevokeResponse, AuthRevokeRequest>() {
}
