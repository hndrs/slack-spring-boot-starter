package io.olaph.slack.broker.security

import org.apache.commons.codec.digest.HmacAlgorithms
import org.apache.commons.codec.digest.HmacUtils
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import org.springframework.web.util.UriUtils
import java.nio.charset.Charset
import java.time.Duration
import java.time.Instant
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class VerificationHandlerInterceptor(private val signingSecret: String) : HandlerInterceptorAdapter() {

    companion object {
        private const val SLACK_SIGNATURE_HEADER_NAME = "x-slack-signature"
        private const val SLACK_REQUEST_TIMESTAMP_HEADER_NAME = "x-slack-request-timestamp"
        private const val SLACK_SIGNATURE_VERSION = "v0"
    }

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {

        if (handler is HandlerMethod && handler.hasMethodAnnotation(VerifiesSlackSignature::class.java)
                && generateHmacHex(request) != signature(request)) throw VerificationException("Invalid signature")

        return true
    }

    private fun generateHmacHex(request: HttpServletRequest): String {
        return "v0=${HmacUtils(HmacAlgorithms.HMAC_SHA_256, this.signingSecret).hmacHex("$SLACK_SIGNATURE_VERSION:${timestamp(request)}:${encodedBody(request)}")}"
    }

    private fun signature(request: HttpServletRequest): String {
        val signature: String? = request.getHeader(SLACK_SIGNATURE_HEADER_NAME)
        return signature ?: throw VerificationException("No slack signature present")
    }

    private fun encodedBody(request: HttpServletRequest): String {
        return request.parameterMap.entries.joinToString(separator = "&") { "${it.key}=${UriUtils.encode(it.value[0], Charset.forName("UTF-8"))}" }
    }

    private fun timestamp(request: HttpServletRequest): String {
        val timestamp: String? = request.getHeader(SLACK_REQUEST_TIMESTAMP_HEADER_NAME)

        return if (timestamp != null && Instant.now().plus(Duration.ofMinutes(5)).isAfter(Instant.ofEpochSecond(timestamp.toLong()))) {
            timestamp
        } else {
            throw VerificationException("Request timestamp invalid or not present")
        }
    }
}

class VerificationException(message: String) : RuntimeException(message)

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class VerifiesSlackSignature
