package io.hndrs.slack.broker.security

import io.mockk.every
import io.mockk.mockk
import jakarta.servlet.http.HttpServletRequest
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

internal class VerificationArgumentResolverTests {

    @DisplayName("SigningVerification")
    @Test
    fun resolve() {
        val signingSecret = "signingSecret"
        val timestamp = Instant.now().epochSecond.toString()

        val expectedSignature =
            "v0=${HmacUtils(HmacAlgorithms.HMAC_SHA_256, signingSecret).hmacHex("${"v0"}:$timestamp:")}"

        val request = mockk<HttpServletRequest>(relaxed = true) {
            every { getHeader("x-slack-request-timestamp") } returns timestamp
            every { getHeader("x-slack-signature") } returns expectedSignature
        }

        assertDoesNotThrow {
            TestVerificationArgumentResolver(signingSecret).resolveArgument(
                mockk(),
                mockk(),
                mockk {
                    every { nativeRequest } returns request
                },
                mockk(),
            )
        }
    }

    @DisplayName("Slack Timestamp Not Present")
    @Test
    fun timestampNotPresent() {
        val request = mockk<HttpServletRequest>(relaxed = true) {
            every { getHeader("x-slack-signature") } returns "someSig"
            every { getHeader("x-slack-request-timestamp") } returns null
        }

        val assertThrows = assertThrows(VerificationException::class.java) {
            TestVerificationArgumentResolver("signingSecret").resolveArgument(
                mockk(),
                mockk(),
                mockk {
                    every { nativeRequest } returns request
                },
                mockk()
            )
        }

        Assertions.assertEquals("No timestamp present in header", assertThrows.message)
    }

    @DisplayName("Timestamp too old")
    @Test
    fun timestampTooOld() {
        val request = mockk<HttpServletRequest>(relaxed = true) {
            every { getHeader("x-slack-request-timestamp") } returns Instant.now()
                .minus(Duration.ofMinutes(6)).epochSecond.toString()
            every { getHeader("x-slack-signature") } returns "somesig"
        }

        val assertThrows = assertThrows(VerificationException::class.java) {
            TestVerificationArgumentResolver("signingSecret").resolveArgument(
                mockk(),
                mockk(),
                mockk {
                    every { nativeRequest } returns request
                },
                mockk(),
            )
        }

        Assertions.assertEquals("Request timestamp older than 5 minutes", assertThrows.message)
    }

    @DisplayName("Timestamp in future")
    @Test
    fun timestampInFuture() {
        val request = mockk<HttpServletRequest>(relaxed = true) {
            every { getHeader("x-slack-request-timestamp") } returns Instant.now()
                .plus(Duration.ofMinutes(6)).epochSecond.toString()

            every { getHeader("x-slack-signature") } returns "somesig"
        }

        val assertThrows = assertThrows(VerificationException::class.java) {
            TestVerificationArgumentResolver("signingSecret").resolveArgument(
                mockk(),
                mockk(),
                mockk {
                    every { nativeRequest } returns request
                },
                mockk(),
            )
        }

        Assertions.assertEquals("Request timestamp is in the future", assertThrows.message)
    }

    @DisplayName("Slack Signature Not Present")
    @Test
    fun signatureNotPresent() {
        val request = mockk<HttpServletRequest>(relaxed = true) {
            every { getHeader("x-slack-signature") } returns null
            every { getHeader("x-slack-request-timestamp") } returns "timestamp"
        }

        val assertThrows = assertThrows(VerificationException::class.java) {
            TestVerificationArgumentResolver("signingSecret").resolveArgument(
                mockk(),
                mockk(),
                mockk {
                    every { nativeRequest } returns request
                },
                mockk(),
            )
        }

        Assertions.assertEquals("No signature present in header", assertThrows.message)
    }
}

class TestVerificationArgumentResolver(signingSecret: String) : VerificationMethodArgumentResolver(signingSecret) {

    override fun internalResolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        request: ContentCachingRequestWrapper,
        binderFactory: WebDataBinderFactory?,
    ): Any? {
        return null
    }

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return true
    }
}
