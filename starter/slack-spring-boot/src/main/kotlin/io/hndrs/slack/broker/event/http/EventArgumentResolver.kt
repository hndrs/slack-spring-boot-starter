package io.hndrs.slack.broker.event.http

import com.fasterxml.jackson.databind.ObjectMapper
import io.hndrs.slack.broker.event.EventRequest
import io.hndrs.slack.broker.security.VerificationMethodArgumentResolver
import org.springframework.core.MethodParameter
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.method.support.ModelAndViewContainer
import org.springframework.web.util.ContentCachingRequestWrapper

/**
 * Argument resolver that resolves incoming events
 *
 * @param signingSecret the signing secret to verify the request
 */
class EventArgumentResolver(signingSecret: String) : VerificationMethodArgumentResolver(signingSecret) {

    private val objectMapper = Jackson2ObjectMapperBuilder.json().build<ObjectMapper>()

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.getParameterAnnotation(Event::class.java) != null &&
            parameter.parameterType == EventRequest::class.java
    }

    override fun internalResolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        request: ContentCachingRequestWrapper,
        binderFactory: WebDataBinderFactory?,
    ): Any? {
        return objectMapper.readValue(request.inputStream, EventRequest::class.java)
    }
}

/**
 * used to annotate incoming events
 */
@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class Event
