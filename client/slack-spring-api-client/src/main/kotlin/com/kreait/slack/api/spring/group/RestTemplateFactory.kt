package com.kreait.slack.api.spring.group

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializerProvider
import com.kreait.slack.api.spring.group.respond.SlackResponseErrorHandler
import org.springframework.http.converter.FormHttpMessageConverter
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate
import java.time.Instant


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
                .serializerByType(Instant::class.java, InstantToUnixTimestampStringSerializer.INSTANCE)
                .deserializerByType(Instant::class.java, UnixTimestampStringToInstantDeserializer.INSTANCE)
                .build<ObjectMapper>()

        slackApiRestTemplate.messageConverters = listOf(MappingJackson2HttpMessageConverter(objectMapper), FormHttpMessageConverter())
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

    class InstantToUnixTimestampStringSerializer : JsonSerializer<Instant>() {

        companion object {
            val INSTANCE = InstantToUnixTimestampStringSerializer()
        }

        override fun serialize(value: Instant, gen: JsonGenerator, serializers: SerializerProvider?) {
            val microSeconds = (value.nano / 1e3).toLong()

            if (microSeconds == 0L) {
                gen.writeString("${value.epochSecond}")
            } else {
                gen.writeString("${value.epochSecond}.${value.nano / 1000}")
            }
        }

    }

    class UnixTimestampStringToInstantDeserializer : JsonDeserializer<Instant>() {

        companion object {
            val INSTANCE = UnixTimestampStringToInstantDeserializer()
        }

        override fun deserialize(p: JsonParser, ctxt: DeserializationContext?): Instant {
            val split = p.text.split(".").map { it.toLong() }
            return if (split.size == 2) {
                Instant.ofEpochSecond(split[0], split[1] * 1000)
            } else {
                Instant.ofEpochSecond(split[0])
            }
        }
    }
}
