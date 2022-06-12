package io.hndrs.slack.broker

import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.every
import io.mockk.mockk
import org.apache.commons.codec.digest.HmacAlgorithms
import org.apache.commons.codec.digest.HmacUtils
import org.springframework.core.MethodParameter
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.web.context.request.NativeWebRequest
import java.net.URLEncoder
import java.nio.charset.Charset
import java.time.Instant

object RequestTestUtils {


    fun jsonBody(value: Any): String {
        val mapper = Jackson2ObjectMapperBuilder.json().build<ObjectMapper>()
        return mapper.writeValueAsString(value)
    }

    fun mockMethodParameter(clazz: Class<*>, annotationClass: Class<out Annotation>): MethodParameter {
        val parameter = mockk<MethodParameter> {
            every { getParameterAnnotation(any<Class<Annotation>>()) } answers {
                println(" i was here")
                if (firstArg<Class<Annotation>>() == annotationClass) {
                    return@answers mockk<Annotation>()
                } else null
            }
            every { parameterType } returns clazz
        }
        return parameter
    }

    fun mockNativeWebRequest(timestamp: Instant, signingSecret: String, body: String): NativeWebRequest {
        val mockRequest = MockHttpServletRequest()
        val generatedHmacHex = generateHmacHex(body, timestamp, signingSecret)
        mockRequest.addHeader("x-slack-signature", generatedHmacHex)
        mockRequest.addHeader("x-slack-request-timestamp", "${timestamp.epochSecond}")
        mockRequest.setContent(body.toByteArray(Charset.forName("UTF-8")))
        return mockk {
            every { nativeRequest } returns mockRequest
        }
    }

    fun mockNativeWebRequest(timestamp: Instant, signingSecret: String, params: Map<String, List<String>>): NativeWebRequest {
        val mockRequest = MockHttpServletRequest()
        val body = toFormUrlEncodedString(params)
        val generatedHmacHex = generateHmacHex(body, timestamp, signingSecret)
        mockRequest.addHeader("x-slack-signature", generatedHmacHex)
        mockRequest.addHeader("x-slack-request-timestamp", "${timestamp.epochSecond}")
        mockRequest.setContent(body.toByteArray(Charset.forName("UTF-8")))
        mockRequest.setParameters(toSupportedParameterMap(params))
        mockRequest.contentType = MediaType.APPLICATION_FORM_URLENCODED_VALUE
        mockRequest.method = HttpMethod.POST.name
        mockRequest.characterEncoding = "UTF-8"

        return mockk {
            every { nativeRequest } returns mockRequest
        }
    }

    fun toFormUrlString(params: Map<String, List<String>>): String {
        return params.flatMap { it.value.map { value -> "${it.key}=$value" } }.joinToString("&")
    }

    fun toFormUrlEncodedString(params: Map<String, List<String>>): String {
        return params.flatMap { it.value.map { value -> "${URLEncoder.encode(it.key, "UTF8")}=${URLEncoder.encode(value, "UTF-8")}" } }.joinToString("&")
    }


    fun toSupportedParameterMap(params: Map<String, List<String>>): Map<String, Array<String>> {
        return params.entries.associate { it.key to it.value.toTypedArray() }
    }

    fun generateHmacHex(requestBody: String, timestamp: Instant, signingSecret: String): String {
        return "v0=${HmacUtils(HmacAlgorithms.HMAC_SHA_256, signingSecret).hmacHex("v0:${timestamp.epochSecond}:$requestBody")}"
    }

    annotation class TestAnnotation
}
