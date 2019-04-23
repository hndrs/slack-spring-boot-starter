package io.olaph.slack.client.spring.group.oauth


import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.oauth.OauthAccessMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.oauth.ErrorOauthAccessResponse
import io.olaph.slack.dto.jackson.group.oauth.OauthAccessResponse
import io.olaph.slack.dto.jackson.group.oauth.SuccessFullOauthAccessResponse
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultOauthAccessMethod(private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : OauthAccessMethod() {

    override fun request(): ApiCallResult<SuccessFullOauthAccessResponse, ErrorOauthAccessResponse> {
        val response = SlackRequestBuilder<OauthAccessResponse>(restTemplate = restTemplate)
                .toMethod("oauth.access")
                .returnAsType(OauthAccessResponse::class.java)
                .postUrlEncoded(mapOf(Pair("client_id", params.clientId),
                        Pair("client_secret", params.client_secret),
                        Pair("code", params.code)))

        return when (response.body!!) {
            is SuccessFullOauthAccessResponse -> {
                val responseEntity = response.body as SuccessFullOauthAccessResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorOauthAccessResponse -> {
                val responseEntity = response.body as ErrorOauthAccessResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
