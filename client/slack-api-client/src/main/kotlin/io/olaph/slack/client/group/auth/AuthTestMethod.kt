package io.olaph.slack.client.group.auth

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.auth.ErrorAuthTestResponse
import io.olaph.slack.dto.jackson.group.auth.SuccessfulAuthTestResponse

abstract class AuthTestMethod : ApiCallMethod<AuthTestMethod, SuccessfulAuthTestResponse, ErrorAuthTestResponse, Unit>()
