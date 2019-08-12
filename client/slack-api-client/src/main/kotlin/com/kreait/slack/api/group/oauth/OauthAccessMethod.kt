package com.kreait.slack.api.group.oauth

import com.kreait.slack.api.contract.jackson.group.oauth.ErrorAccessResponse
import com.kreait.slack.api.contract.jackson.group.oauth.AccessRequest
import com.kreait.slack.api.contract.jackson.group.oauth.SuccessfullAccessResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class OauthAccessMethod : ApiCallMethod<OauthAccessMethod, SuccessfullAccessResponse, ErrorAccessResponse, AccessRequest>()
