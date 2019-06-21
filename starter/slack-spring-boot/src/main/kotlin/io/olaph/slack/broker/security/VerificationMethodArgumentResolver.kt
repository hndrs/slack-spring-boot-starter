package io.olaph.slack.broker.security

import org.apache.commons.codec.digest.HmacAlgorithms
import org.apache.commons.codec.digest.HmacUtils
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import org.springframework.web.util.ContentCachingRequestWrapper
import java.nio.charset.Charset
import java.time.Duration
import java.time.Instant
import javax.servlet.http.HttpServletRequest

class VerificationException(message: String) : RuntimeException(message)

/**
 * https://api.slack.com/docs/verifying-requests-from-slack
 */
abstract class VerificationMethodArgumentResolver(private val signingSecret: String) : HandlerMethodArgumentResolver {

    companion object {
        private const val SLACK_SIGNATURE_HEADER_NAME = "x-slack-signature"
        private const val SLACK_REQUEST_TIMESTAMP_HEADER_NAME = "x-slack-request-timestamp"
        private const val SLACK_SIGNATURE_VERSION = "v0"
    }

    final override fun resolveArgument(parameter: MethodParameter, mavContainer: ModelAndViewContainer?, webRequest: NativeWebRequest, binderFactory: WebDataBinderFactory?): Any? {
        val cachedRequestWrapper = ContentCachingRequestWrapper(webRequest.nativeRequest as HttpServletRequest)
        val resolvedArgument = internalResolveArgument(parameter, mavContainer, cachedRequestWrapper, binderFactory)

        validateSigning(cachedRequestWrapper, this.signingSecret)

        return resolvedArgument
    }

    protected abstract fun internalResolveArgument(parameter: MethodParameter, mavContainer: ModelAndViewContainer?, request: ContentCachingRequestWrapper, binderFactory: WebDataBinderFactory?): Any?


    private fun validateSigning(request: ContentCachingRequestWrapper, signingSecret: String) {

        val slackSignature = request.getHeader(SLACK_SIGNATURE_HEADER_NAME)
                ?: throw VerificationException("No signature present in header")
        val slackTimestamp = request.getHeader(SLACK_REQUEST_TIMESTAMP_HEADER_NAME)
                ?: throw VerificationException("No timestamp present in header")

        this.validateTimeStamp(slackTimestamp, Instant.now())

        val hmac = generateHmacHex(String(request.contentAsByteArray, Charset.forName("UTF-8")), slackTimestamp, signingSecret)

        if (hmac != slackSignature) throw VerificationException("InvalidSignature")
    }


    private fun generateHmacHex(requestBody: String, slackTimeStamp: String, signingSecret: String): String {
        return "v0=${HmacUtils(HmacAlgorithms.HMAC_SHA_256, signingSecret).hmacHex("$SLACK_SIGNATURE_VERSION:$slackTimeStamp:${requestBody.replace("*", "%2A")}")}"
    }

    private fun validateTimeStamp(slackTimestamp: String, currentTime: Instant): String {

        val requestTimestamp = Instant.ofEpochSecond(slackTimestamp.toLong())

        if (requestTimestamp.isAfter(Instant.now())) {
            throw VerificationException("Request timestamp is in the future")
        }

        val compareTo = Duration.between(requestTimestamp, currentTime)
                .compareTo(Duration.ofMinutes(5))

        return if (compareTo < 0) {
            slackTimestamp
        } else {
            throw VerificationException("Request timestamp older than 5 minutes")
        }
    }

}
