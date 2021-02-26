package io.hndrs.slack.api.group.auth

import io.hndrs.slack.api.contract.jackson.group.auth.ErrorAuthTestResponse
import io.hndrs.slack.api.contract.jackson.group.auth.SuccessfulAuthTestResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Checks authentication & identity.
 * https://api.slack.com/methods/auth.test
 */
abstract class AuthTestMethod : io.hndrs.slack.api.group.ApiCallMethod<io.hndrs.slack.api.group.auth.AuthTestMethod, SuccessfulAuthTestResponse, ErrorAuthTestResponse, Unit>()
