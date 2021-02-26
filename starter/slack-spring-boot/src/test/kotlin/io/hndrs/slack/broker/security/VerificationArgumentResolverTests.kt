package io.hndrs.slack.broker.security

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.apache.commons.codec.digest.HmacAlgorithms
import org.apache.commons.codec.digest.HmacUtils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.method.support.ModelAndViewContainer
import org.springframework.web.util.ContentCachingRequestWrapper
import java.time.Duration
import java.time.Instant
import javax.servlet.http.HttpServletRequest

internal class VerificationArgumentResolverTests {

    @DisplayName("SigningVerification")
    @Test
    fun resolve() {

        val signingSecret = "signingSecret"
        val timestamp = Instant.now().epochSecond.toString()

        val expectedSignature = "v0=${HmacUtils(HmacAlgorithms.HMAC_SHA_256, signingSecret).hmacHex("${"v0"}:$timestamp:")}"


        val request: HttpServletRequest = mock { on { getHeader("x-slack-request-timestamp") } doReturn timestamp }
        whenever(request.getHeader("x-slack-signature")).thenReturn(expectedSignature)
        whenever(request.parameterMap).thenReturn(mapOf())

        assertDoesNotThrow { TestVerificationArgumentResolver(signingSecret).resolveArgument(mock { }, mock { }, mock { on { nativeRequest } doReturn request }, mock { }) }
    }

    @DisplayName("Slack Timestamp Not Present")
    @Test
    fun timestampNotPresent() {
        val request: HttpServletRequest = mock { on { getHeader("x-slack-signature") } doReturn "someSig" }

        val assertThrows = assertThrows(VerificationException::class.java) { TestVerificationArgumentResolver("signingSecret").resolveArgument(mock { }, mock { }, mock { on { nativeRequest } doReturn request }, mock { }) }

        Assertions.assertEquals("No timestamp present in header", assertThrows.message)
    }

    @DisplayName("Timestamp too old")
    @Test
    fun timestampTooOld() {
        val request: HttpServletRequest = mock { on { getHeader("x-slack-signature") } doReturn "someSig" }
        whenever(request.getHeader("x-slack-request-timestamp")).thenReturn(Instant.now().minus(Duration.ofMinutes(6)).epochSecond.toString())

        val assertThrows = assertThrows(VerificationException::class.java) { TestVerificationArgumentResolver("signingSecret").resolveArgument(mock { }, mock { }, mock { on { nativeRequest } doReturn request }, mock { }) }

        Assertions.assertEquals("Request timestamp older than 5 minutes", assertThrows.message)
    }

    @DisplayName("Timestamp in future")
    @Test
    fun timestampInFuture() {
        val request: HttpServletRequest = mock { on { getHeader("x-slack-signature") } doReturn "someSig" }
        whenever(request.getHeader("x-slack-request-timestamp")).thenReturn(Instant.now().plus(Duration.ofMinutes(6)).epochSecond.toString())

        val assertThrows = assertThrows(VerificationException::class.java) { TestVerificationArgumentResolver("signingSecret").resolveArgument(mock { }, mock { }, mock { on { nativeRequest } doReturn request }, mock { }) }

        Assertions.assertEquals("Request timestamp is in the future", assertThrows.message)
    }

    @DisplayName("Slack Signature Not Present")
    @Test
    fun signatureNotPresent() {
        val request: HttpServletRequest = mock { on { getHeader("x-slack-request-timestamp") } doReturn "timestamp" }

        val assertThrows = assertThrows(VerificationException::class.java) { TestVerificationArgumentResolver("signingSecret").resolveArgument(mock { }, mock { }, mock { on { nativeRequest } doReturn request }, mock { }) }

        Assertions.assertEquals("No signature present in header", assertThrows.message)
    }

}

class TestVerificationArgumentResolver(signingSecret: String) : VerificationMethodArgumentResolver(signingSecret) {

    override fun internalResolveArgument(parameter: MethodParameter, mavContainer: ModelAndViewContainer?, request: ContentCachingRequestWrapper, binderFactory: WebDataBinderFactory?): Any? {
        return null
    }

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return true
    }

}
