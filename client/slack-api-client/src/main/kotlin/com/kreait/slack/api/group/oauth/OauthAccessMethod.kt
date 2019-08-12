package com.kreait.slack.api.group.oauth

import com.kreait.slack.api.group.ApiCallMethod
import com.kreait.slack.api.contract.jackson.group.oauth.ErrorOauthAccessResponse
import com.kreait.slack.api.contract.jackson.group.oauth.OauthAccessRequest
import com.kreait.slack.api.contract.jackson.group.oauth.SuccessFullOauthAccessResponse

abstract class OauthAccessMethod : ApiCallMethod<OauthAccessMethod, SuccessFullOauthAccessResponse, ErrorOauthAccessResponse, OauthAccessRequest>()
