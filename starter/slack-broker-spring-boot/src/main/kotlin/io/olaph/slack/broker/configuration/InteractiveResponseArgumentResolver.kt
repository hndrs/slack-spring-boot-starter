package io.olaph.slack.broker.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import io.olaph.slack.dto.jackson.group.dialog.InteractiveComponentResponse
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import javax.servlet.http.HttpServletRequest

class InteractiveResponseArgumentResolver : HandlerMethodArgumentResolver {

    private val objectMapper = ObjectMapper()

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.getParameterAnnotation(InteractiveResponse::class.java) != null
    }

    override fun resolveArgument(parameter: MethodParameter, mavContainer: ModelAndViewContainer?, webRequest: NativeWebRequest, binderFactory: WebDataBinderFactory?): Any? {
        val request = webRequest.nativeRequest as HttpServletRequest
        val associateMap = request.parameterMap.entries.associate { it.key to it.value[0] }
        
        return objectMapper.readValue(associateMap["payload"], InteractiveComponentResponse::class.java)
    }
}

annotation class InteractiveResponse
