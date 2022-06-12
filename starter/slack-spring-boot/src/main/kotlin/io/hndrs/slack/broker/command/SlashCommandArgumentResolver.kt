package io.hndrs.slack.broker.command

import com.fasterxml.jackson.databind.ObjectMapper
import io.hndrs.slack.broker.security.VerificationMethodArgumentResolver
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.method.support.ModelAndViewContainer
import org.springframework.web.util.ContentCachingRequestWrapper

/**
 * Argument resolver that resolves incoming commands
 *
 * @param signingSecret the signing secret to verify the request
 */
class SlashCommandArgumentResolver(signingSecret: String) : VerificationMethodArgumentResolver(signingSecret) {

    private val objectMapper = ObjectMapper()

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.getParameterAnnotation(Command::class.java) != null
                && parameter.parameterType == SlashCommand::class.java
    }

    override fun internalResolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        request: ContentCachingRequestWrapper,
        binderFactory: WebDataBinderFactory?,
    ): Any? {
        val associateMap = request.parameterMap.entries.associate { it.key to it.value[0] }
        return objectMapper.convertValue(associateMap, SlashCommand::class.java)
    }
}

/**
 * used to annotate incoming commands
 */
@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class Command
