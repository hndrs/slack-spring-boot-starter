package io.olaph.slack.client.spring.group

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate


/**
 * Holds the configured [RestTemplate] instance that is compliant with the slack api
 */
object RestTemplateFactory {

    val restTemplate = RestTemplate()

    init {

        val objectMapper = Jackson2ObjectMapperBuilder
                .json()
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .failOnUnknownProperties(false)
                .build<ObjectMapper>()

        val mappingJackson2HttpMessageConverter = MappingJackson2HttpMessageConverter(objectMapper)
        mappingJackson2HttpMessageConverter.supportedMediaTypes = listOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED)
        restTemplate.messageConverters = listOf(mappingJackson2HttpMessageConverter)
    }

    /**
     * gets a slack compliant restTemplate
     */
    fun slackTemplate() = restTemplate
}
