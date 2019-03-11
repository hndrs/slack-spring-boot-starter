package io.olaph.slack.client.implementation.group.oauth

import io.olaph.slack.client.UnknownResponseException
import io.olaph.slack.client.implementation.group.SlackRequestBuilder
import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.oauth.OauthAccessMethod
import io.olaph.slack.dto.jackson.group.oauth.ErrorOauthAccessResponse
import io.olaph.slack.dto.jackson.group.oauth.OauthAccessResponse
import io.olaph.slack.dto.jackson.group.oauth.SuccessFullOauthAccessResponse

@Suppress("UNCHECKED_CAST")
class DefaultOauthAccessMethod : OauthAccessMethod() {

    override fun request(): ApiCallResult<SuccessFullOauthAccessResponse, ErrorOauthAccessResponse> {
        val response = SlackRequestBuilder<OauthAccessResponse>()
                .toMethod("oauth.access")
                .returnAsType(OauthAccessResponse::class.java)
                .postUrlEncoded(mapOf(Pair("client_id", params.clientId),
                        Pair("client_secret", params.client_secret),
                        Pair("code", params.code)))

        return when {
            response.body is SuccessFullOauthAccessResponse -> {
                val responseEntity = response.body as SuccessFullOauthAccessResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            response.body is ErrorOauthAccessResponse -> {
                val responseEntity = response.body as ErrorOauthAccessResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
            else -> {
                throw UnknownResponseException(this::class, response)
            }
        }
    }
}
