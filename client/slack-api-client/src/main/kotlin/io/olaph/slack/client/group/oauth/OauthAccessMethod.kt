package io.olaph.slack.client.group.oauth

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.oauth.ErrorOauthAccessResponse
import io.olaph.slack.dto.jackson.group.oauth.OauthAccessRequest
import io.olaph.slack.dto.jackson.group.oauth.SuccessFullOauthAccessResponse

abstract class OauthAccessMethod : ApiCallMethod<OauthAccessMethod, SuccessFullOauthAccessResponse, ErrorOauthAccessResponse, OauthAccessRequest>()
