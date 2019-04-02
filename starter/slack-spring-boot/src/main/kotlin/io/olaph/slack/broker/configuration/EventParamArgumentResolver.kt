package io.olaph.slack.broker.configuration

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import io.olaph.slack.broker.security.VerificationMethodArgumentResolver
import io.olaph.slack.dto.jackson.EventRequest
import org.springframework.core.MethodParameter
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.method.support.ModelAndViewContainer
import org.springframework.web.util.ContentCachingRequestWrapper

class EventRequestArgumentResolver(signingSecret: String?) : VerificationMethodArgumentResolver(signingSecret) {

    private val objectMapper = Jackson2ObjectMapperBuilder.json().build<ObjectMapper>()

    init {
        objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION)
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
    }

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.getParameterAnnotation(Event::class.java) != null
    }

    override fun internalResolveArgument(parameter: MethodParameter, mavContainer: ModelAndViewContainer?, request: ContentCachingRequestWrapper, binderFactory: WebDataBinderFactory?): Any? {
        return objectMapper.readValue(request.inputStream, EventRequest::class.java)
    }
}

annotation class Event
