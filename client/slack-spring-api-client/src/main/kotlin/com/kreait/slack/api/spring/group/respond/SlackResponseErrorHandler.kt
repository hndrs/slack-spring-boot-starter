package com.kreait.slack.api.spring.group.respond

import org.slf4j.LoggerFactory
import org.springframework.http.client.ClientHttpResponse
import org.springframework.web.client.ResponseErrorHandler

/**
 * Error-handler that logs occurring errors
 *
 */
class SlackResponseErrorHandler : ResponseErrorHandler {
    companion object {
        private val Log = LoggerFactory.getLogger(SlackResponseErrorHandler::class.java)
    }

    override fun hasError(response: ClientHttpResponse): Boolean {
        return !response.statusCode.is2xxSuccessful
    }

    override fun handleError(response: ClientHttpResponse) {
        Log.error("received error-code: ${response.statusCode} while posting to responseUrl")
    }
}
