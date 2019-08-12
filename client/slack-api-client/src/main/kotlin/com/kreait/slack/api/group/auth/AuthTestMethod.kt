package com.kreait.slack.api.group.auth

import com.kreait.slack.api.contract.jackson.group.auth.ErrorAuthTestResponse
import com.kreait.slack.api.contract.jackson.group.auth.SuccessfulAuthTestResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class AuthTestMethod : ApiCallMethod<AuthTestMethod, SuccessfulAuthTestResponse, ErrorAuthTestResponse, Unit>()
