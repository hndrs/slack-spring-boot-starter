package com.kreait.slack.api.spring.group.im


import com.kreait.slack.api.contract.jackson.group.im.ErrorImListResponse
import com.kreait.slack.api.contract.jackson.group.im.ImListResponse
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImListResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.im.ImListMethod
import com.kreait.slack.api.group.im.ImMethodGroup
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * Spring based implementation of [ImMethodGroup.list]
 */
@Suppress("UNCHECKED_CAST")
class SpringImListMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ImListMethod() {

    override fun request(): ApiCallResult<SuccessfulImListResponse, ErrorImListResponse> {
        val response = SlackRequestBuilder<ImListResponse>(authToken, restTemplate)
                .toMethod("im.listGroups")
                .returnAsType(ImListResponse::class.java)
                .postUrlEncoded(this.params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulImListResponse -> {
                val responseEntity = response.body as SuccessfulImListResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorImListResponse -> {
                val responseEntity = response.body as ErrorImListResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
