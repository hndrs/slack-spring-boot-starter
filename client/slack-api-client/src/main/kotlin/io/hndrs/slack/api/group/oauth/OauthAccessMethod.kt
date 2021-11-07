package io.hndrs.slack.api.group.oauth

import io.hndrs.slack.api.contract.jackson.group.oauth.AccessRequest
import io.hndrs.slack.api.contract.jackson.group.oauth.ErrorAccessResponse
import io.hndrs.slack.api.contract.jackson.group.oauth.SuccessfullAccessResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/oauth.access
 */
abstract class OauthAccessMethod :
    ApiCallMethod<OauthAccessMethod, SuccessfullAccessResponse, ErrorAccessResponse, AccessRequest>()
