package io.olaph.slack.client.group.auth

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.auth.ErrorAuthResponse
import io.olaph.slack.dto.jackson.group.auth.SuccessfulAuthResponse

abstract class AuthTestMethod : ApiCallMethod<AuthTestMethod, SuccessfulAuthResponse, ErrorAuthResponse, Unit>()
