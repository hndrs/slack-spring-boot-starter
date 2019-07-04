package io.olaph.slack.client.spring.group

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import io.olaph.slack.client.spring.group.respond.SlackResponseErrorHandler
import org.springframework.http.MediaType
import org.springframework.http.converter.FormHttpMessageConverter
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate


/**
 * Holds the configured [RestTemplate] instance that is compliant with the slack api
 */
object RestTemplateFactory {

    private val slackApiRestTemplate = RestTemplate()
    private val slackResponseRestTemplate = RestTemplate()
    private val formUrlTemplate = RestTemplate()

    init {

        val objectMapper = Jackson2ObjectMapperBuilder
                .json()
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .failOnUnknownProperties(false)
                .build<ObjectMapper>()

        val mappingJackson2HttpMessageConverter = MappingJackson2HttpMessageConverter(objectMapper)
        mappingJackson2HttpMessageConverter.supportedMediaTypes = listOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED)
        slackApiRestTemplate.messageConverters = listOf(mappingJackson2HttpMessageConverter)
        formUrlTemplate.messageConverters.add(FormHttpMessageConverter())
        slackResponseRestTemplate.errorHandler = SlackResponseErrorHandler()
    }

    /**
     * gets a slack compliant slackApiRestTemplate
     */
    fun slackTemplate() = slackApiRestTemplate


    /**
     * gets a slack compliant template for Form url encoded requests
     */
    fun formUrlTemplate() = formUrlTemplate

    /**
     *  gets a slackApiRestTemplate which logs errors without interrupting
     */
    fun slackResponseTemplate() = slackResponseRestTemplate
}
