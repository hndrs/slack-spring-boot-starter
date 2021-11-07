package io.hndrs.slack.api.spring.group.oauth


import io.hndrs.slack.api.contract.jackson.group.oauth.AccessResponse
import io.hndrs.slack.api.contract.jackson.group.oauth.ErrorAccessResponse
import io.hndrs.slack.api.contract.jackson.group.oauth.SuccessfullAccessResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.oauth.OauthAccessMethod
import io.hndrs.slack.api.group.oauth.OauthMethodGroup
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [OauthMethodGroup.access]
 */
@Suppress("UNCHECKED_CAST")
class SpringOauthAccessMethod(private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) :
    OauthAccessMethod() {

    override fun request(): ApiCallResult<SuccessfullAccessResponse, ErrorAccessResponse> {
        val response = SlackRequestBuilder<AccessResponse>(restTemplate = restTemplate)
            .toMethod("oauth.v2.access")
            .returnAsType(AccessResponse::class.java)
            .postUrlEncoded(
                mapOf(
                    Pair("client_id", params.clientId),
                    Pair("client_secret", params.client_secret),
                    Pair("code", params.code)
                )
            )

        return when (response.body!!) {
            is SuccessfullAccessResponse -> {
                val responseEntity = response.body as SuccessfullAccessResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorAccessResponse -> {
                val responseEntity = response.body as ErrorAccessResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
