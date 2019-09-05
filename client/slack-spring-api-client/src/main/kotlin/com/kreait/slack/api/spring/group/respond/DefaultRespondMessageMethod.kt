package com.kreait.slack.api.spring.group.respond


import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.respond.RespondMessageMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange
import java.net.URI


/**
 * Posts a Ephemeral message to a responseUrl which is only visible to a specific user
 * @param config the configuration of the Slackclient
 * @return the API Call Method containing the ResponseEntities
 */
@Suppress("UNCHECKED_CAST")
class DefaultRespondMessageMethod(private val responseUrl: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackResponseTemplate()) : RespondMessageMethod() {

    override fun request(): ApiCallResult<Unit, Unit> {
        
        val uri = URI.create(responseUrl)
        val requestEntity = RequestEntity(this.params, HttpMethod.POST, uri)
        val response = restTemplate.exchange<Unit>(requestEntity)
        return if (response.statusCode.is2xxSuccessful) {
            this.onSuccess?.invoke(Unit)
            ApiCallResult(success = Unit)
        } else {
            this.onFailure?.invoke(Unit)
            ApiCallResult(failure = Unit)
        }
    }
}
