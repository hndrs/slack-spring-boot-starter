package io.olaph.slack.broker.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.apache.commons.codec.digest.HmacAlgorithms
import org.apache.commons.codec.digest.HmacUtils
import org.springframework.core.MethodParameter
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.web.context.request.NativeWebRequest
import java.nio.charset.Charset
import java.time.Instant

object ArgumentResolverTestUtil {


    fun jsonBody(value: Any): String {
        val mapper = Jackson2ObjectMapperBuilder.json().build<ObjectMapper>()
        return mapper.writeValueAsString(value)
    }

    fun mockMethodParameter(clazz: Class<*>, annotationClass: Class<out Annotation>): MethodParameter {
        val parameter = mock<MethodParameter> { on { it.getParameterAnnotation(annotationClass) } doReturn mock {} }
        doReturn(clazz).`when`(parameter).parameterType
        return parameter
    }

    fun mockNativeWebRequest(timestamp: Instant, signingSecret: String, body: String, params: Map<String, Array<out String>>): NativeWebRequest {
        val mockRequest = MockHttpServletRequest()
        val generatedHmacHex = generateHmacHex(body, "${timestamp.epochSecond}", signingSecret)
        mockRequest.addHeader("x-slack-signature", generatedHmacHex)
        mockRequest.addHeader("x-slack-request-timestamp", "${timestamp.epochSecond}")
        mockRequest.contentAsByteArray
        mockRequest.setContent(body.toByteArray(Charset.forName("UTF-8")))
        mockRequest.setParameters(params)

        return mock { on { it.nativeRequest } doReturn mockRequest }
    }

    private fun generateHmacHex(requestBody: String, slackTimeStamp: String, signingSecret: String): String {
        return "v0=${HmacUtils(HmacAlgorithms.HMAC_SHA_256, signingSecret).hmacHex("v0:$slackTimeStamp:$requestBody")}"
    }

    annotation class TestAnnotation
}
