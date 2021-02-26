package io.hndrs.slack.broker.security

import org.apache.commons.codec.digest.HmacAlgorithms
import org.apache.commons.codec.digest.HmacUtils
import org.springframework.core.MethodParameter
import org.springframework.http.MediaType
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import org.springframework.web.util.ContentCachingRequestWrapper
import java.nio.charset.Charset
import java.time.Duration
import java.time.Instant
import javax.servlet.http.HttpServletRequest

/**
 * thrown when a request could not be verified
 */
class VerificationException(message: String) : RuntimeException(message)

/**
 * https://api.slack.com/docs/verifying-requests-from-slack
 */
@SuppressWarnings("detekt:ThrowsCount")
abstract class VerificationMethodArgumentResolver(private val signingSecret: String) : HandlerMethodArgumentResolver {

    final override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        val cachedRequestWrapper = ContentCachingRequestWrapper(webRequest.nativeRequest as HttpServletRequest)
        val resolvedArgument = internalResolveArgument(parameter, mavContainer, cachedRequestWrapper, binderFactory)

        validateSigning(cachedRequestWrapper, this.signingSecret)

        return resolvedArgument
    }

    protected abstract fun internalResolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        request: ContentCachingRequestWrapper,
        binderFactory: WebDataBinderFactory?
    ): Any?


    private fun validateSigning(request: ContentCachingRequestWrapper, signingSecret: String) {

        val slackSignature = request.getHeader(SLACK_SIGNATURE_HEADER_NAME)
            ?: throw VerificationException("No signature present in header")
        val slackTimestamp = request.getHeader(SLACK_REQUEST_TIMESTAMP_HEADER_NAME)
            ?: throw VerificationException("No timestamp present in header")

        this.validateTimeStamp(slackTimestamp, Instant.now())

        val body = when (request.getHeader(CONTENT_TYPE)) {
            MediaType.APPLICATION_FORM_URLENCODED_VALUE -> replaceSpecialChars(
                String(
                    request.contentAsByteArray,
                    Charset.forName("UTF-8")
                )
            )
            else -> String(request.contentAsByteArray, Charset.forName("UTF-8"))
        }

        val hmac = generateHmacHex(body, slackTimestamp, signingSecret)
        if (hmac != slackSignature) throw VerificationException("InvalidSignature")
    }


    private fun generateHmacHex(requestBody: String, slackTimeStamp: String, signingSecret: String): String {
        return "v0=${
            HmacUtils(
                HmacAlgorithms.HMAC_SHA_256,
                signingSecret
            ).hmacHex("$SLACK_SIGNATURE_VERSION:$slackTimeStamp:$requestBody")
        }"
    }

    private fun validateTimeStamp(slackTimestamp: String, currentTime: Instant): String {

        val requestTimestamp = Instant.ofEpochSecond(slackTimestamp.toLong())

        if (requestTimestamp.isAfter(Instant.now())) {
            throw VerificationException("Request timestamp is in the future")
        }

        val compareTo = Duration.between(requestTimestamp, currentTime)
            .compareTo(Duration.ofMinutes(SLACK_MAX_AGE))

        return if (compareTo < 0) {
            slackTimestamp
        } else {
            throw VerificationException("Request timestamp older than 5 minutes")
        }
    }

    companion object {

        private const val SLACK_SIGNATURE_HEADER_NAME = "x-slack-signature"
        private const val SLACK_REQUEST_TIMESTAMP_HEADER_NAME = "x-slack-request-timestamp"
        private const val CONTENT_TYPE = "Content-Type"
        private const val SLACK_SIGNATURE_VERSION = "v0"
        private val specialChars = mapOf("*" to "%2A")
        private const val SLACK_MAX_AGE: Long = 5

        /**
         * since spring decodes unreserved characters automatically
         * we need to replace them with their url escaped value again
         * http://www.rfc-editor.org/rfc/rfc1738.txt
         * May result in failures when there are special chars in parameter names
         */
        private fun replaceSpecialChars(payload: String): String {
            var replacedPayload = payload
            specialChars.forEach {
                replacedPayload = replacedPayload.replace(it.key, it.value)
            }
            return replacedPayload
        }
    }
}
