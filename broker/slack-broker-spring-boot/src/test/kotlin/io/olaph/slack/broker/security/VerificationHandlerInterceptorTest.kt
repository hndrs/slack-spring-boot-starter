package io.olaph.slack.broker.security

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.apache.commons.codec.digest.HmacAlgorithms
import org.apache.commons.codec.digest.HmacUtils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.web.method.HandlerMethod
import java.time.Instant
import javax.servlet.http.HttpServletRequest

internal class VerificationHandlerInterceptorTest {

    @DisplayName("SigningVerification")
    @Test
    fun preHandle() {

        val signingSecret = "signingSecret"
        val timestamp = Instant.now().epochSecond.toString()

        val expectedSignature = "v0=${HmacUtils(HmacAlgorithms.HMAC_SHA_256, signingSecret).hmacHex("${"v0"}:$timestamp:")}"


        val request: HttpServletRequest = mock { on { getHeader("x-slack-request-timestamp") } doReturn timestamp }
        whenever(request.getHeader("x-slack-signature")).thenReturn(expectedSignature)
        whenever(request.parameterMap).thenReturn(mapOf())

        val handler: HandlerMethod = mock { on { it.hasMethodAnnotation(VerifiesSlackSignature::class.java) } doReturn true }

        assertTrue(VerificationHandlerInterceptor(signingSecret).preHandle(request, mock { }, handler))
    }


    @DisplayName("Slack Timestamp Not Present")
    @Test
    fun timeStampNotPresent() {
        val request: HttpServletRequest = mock { on { getHeader("x-slack-request-timestamp") } doReturn null }
        val handler: HandlerMethod = mock { on { it.hasMethodAnnotation(VerifiesSlackSignature::class.java) } doReturn true }

        val assertThrows = assertThrows<VerificationException> { VerificationHandlerInterceptor("signingSecret").preHandle(request, mock { }, handler) }
        Assertions.assertEquals("Request timestamp invalid or not present", assertThrows.message)
    }

    @DisplayName("Slack Signature Not Present")
    @Test
    fun signatureNotPresent() {
        val request: HttpServletRequest = mock { on { getHeader("x-slack-request-timestamp") } doReturn Instant.now().epochSecond.toString() }
        whenever(request.getHeader("x-slack-signature")).thenReturn(null)

        val handler: HandlerMethod = mock { on { it.hasMethodAnnotation(VerifiesSlackSignature::class.java) } doReturn true }

        val assertThrows = assertThrows<VerificationException> { VerificationHandlerInterceptor("signingSecret").preHandle(request, mock { }, handler) }
        Assertions.assertEquals("No slack signature present", assertThrows.message)
    }

    @DisplayName("Not Annotated")
    @Test
    fun notAnnotated() {
        val handler: HandlerMethod = mock { on { it.hasMethodAnnotation(VerifiesSlackSignature::class.java) } doReturn false }
        assertTrue(VerificationHandlerInterceptor("signingSecret").preHandle(mock {}, mock { }, handler))
    }

    @DisplayName("Not HandlerMethod")
    @Test
    fun notHandlerMethod() {
        assertTrue(VerificationHandlerInterceptor("signingSecret").preHandle(mock {}, mock { }, mock { }))
    }
}
