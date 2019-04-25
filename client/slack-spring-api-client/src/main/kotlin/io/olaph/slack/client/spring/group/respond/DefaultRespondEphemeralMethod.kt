package io.olaph.slack.client.spring.group.respond


import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.respond.RespondEphemeralMethod
import org.slf4j.LoggerFactory
import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity
import org.springframework.http.client.ClientHttpResponse
import org.springframework.web.client.ResponseErrorHandler
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange
import java.net.URI


/**
 * Posts a Ephemeral message to a responseUrl which is only visible to a specific user
 * @param config the configuration of the Slackclient
 * @return the API Call Method containing the ResponseEntities
 */
@Suppress("UNCHECKED_CAST")
class DefaultRespondEphemeralMethod(private val responseUrl: String, private val restTemplate: RestTemplate = RestTemplate()) : RespondEphemeralMethod() {

    override fun request(): ApiCallResult<Unit, Unit> {

        val uri = URI.create(responseUrl)

        val requestEntity = RequestEntity(this.params, HttpMethod.POST, uri)
        restTemplate.errorHandler = SlackResponseErrorHandler()
        val response = restTemplate.exchange<String>(requestEntity)
        return if (response.statusCode.is2xxSuccessful) {
            this.onSuccess?.invoke(Unit)
            ApiCallResult(success = Unit)
        } else {
            this.onFailure?.invoke(Unit)
            ApiCallResult(failure = Unit)
        }
    }
}
