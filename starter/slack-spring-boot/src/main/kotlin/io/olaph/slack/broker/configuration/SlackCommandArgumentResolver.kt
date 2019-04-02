package io.olaph.slack.broker.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import io.olaph.slack.broker.security.VerificationMethodArgumentResolver
import io.olaph.slack.dto.jackson.SlackCommand
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.method.support.ModelAndViewContainer
import org.springframework.web.util.ContentCachingRequestWrapper

class SlackCommandArgumentResolver(signingSecret: String?) : VerificationMethodArgumentResolver(signingSecret) {

    private val objectMapper = ObjectMapper()

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.getParameterAnnotation(Command::class.java) != null
    }

    override fun internalResolveArgument(parameter: MethodParameter, mavContainer: ModelAndViewContainer?, request: ContentCachingRequestWrapper, binderFactory: WebDataBinderFactory?): Any? {
        val associateMap = request.parameterMap.entries.associate { it.key to it.value[0] }
        return objectMapper.convertValue(associateMap, SlackCommand::class.java)
    }
}


annotation class Command
