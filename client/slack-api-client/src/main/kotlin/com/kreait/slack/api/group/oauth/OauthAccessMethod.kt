package com.kreait.slack.api.group.oauth

import com.kreait.slack.api.contract.jackson.group.oauth.ErrorOauthAccessResponse
import com.kreait.slack.api.contract.jackson.group.oauth.OauthAccessRequest
import com.kreait.slack.api.contract.jackson.group.oauth.SuccessFullOauthAccessResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class OauthAccessMethod : ApiCallMethod<OauthAccessMethod, SuccessFullOauthAccessResponse, ErrorOauthAccessResponse, OauthAccessRequest>()
