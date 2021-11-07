package io.hndrs.slack.api.group.apps

import io.hndrs.slack.api.contract.jackson.group.auth.AuthRevokeRequest
import io.hndrs.slack.api.contract.jackson.group.auth.ErrorAuthRevokeResponse
import io.hndrs.slack.api.contract.jackson.group.auth.SuccessfulAuthRevokeResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Revokes a token.
 * https://api.slack.com/methods/auth.revoke
 */
abstract class ManifestCreateMethod :
    ApiCallMethod<ManifestCreateMethod, ErrorAuthRevokeResponse, ErrorAuthRevokeResponse, AuthRevokeRequest>() {
}
