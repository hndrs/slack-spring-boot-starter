package com.kreait.slack.api.group.oauth

import com.kreait.slack.api.contract.jackson.group.oauth.AccessRequest
import com.kreait.slack.api.contract.jackson.group.oauth.ErrorAccessResponse
import com.kreait.slack.api.contract.jackson.group.oauth.SuccessfullAccessResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/oauth.access
 */
abstract class OauthAccessMethod :
    ApiCallMethod<OauthAccessMethod, SuccessfullAccessResponse, ErrorAccessResponse, AccessRequest>()
