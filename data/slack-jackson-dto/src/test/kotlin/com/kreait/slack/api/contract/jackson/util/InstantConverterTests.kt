package com.kreait.slack.api.contract.jackson.util

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.jayway.jsonpath.JsonPath
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import java.time.Instant

@DisplayName("Instant Converter Tests")
internal class InstantConverterTests {


    @JacksonDataClass
    data class InstantValueHolder(
        @InstantToInt
        @param:JsonProperty("createdAt")
        @field:JsonProperty("createdAt") val createdAt: Instant,
        @InstantToString
        @param:JsonProperty("ts")
        @field:JsonProperty("ts") val timestamp: Instant
    )

    val mapper = Jackson2ObjectMapperBuilder
        .json()
        .serializationInclusion(JsonInclude.Include.NON_NULL)
        .failOnUnknownProperties(false)
        .build<ObjectMapper>()

    @Test
    @DisplayName("Serialization")
    fun serializationTests() {
        val sampleInstant = Instant.now()


        val serializedValue = mapper.writeValueAsString(InstantValueHolder(sampleInstant, sampleInstant))
        val createdAt = JsonPath.parse(serializedValue).read<Int>("createdAt")
        val timestamp = JsonPath.parse(serializedValue).read<String>("ts")

        Assertions.assertEquals(sampleInstant.epochSecond, createdAt.toLong())
        Assertions.assertEquals("${sampleInstant.epochSecond}.${sampleInstant.micros()}", timestamp)
    }

    @Test
    @DisplayName("Deserialization")
    fun deserializationTests() {
        val sampleInstant = Instant.now()
        val sample =
            "{\"createdAt\":${sampleInstant.epochSecond},\"ts\":${sampleInstant.epochSecond}.${sampleInstant.micros()}}"

        val deserializedValue = mapper.readValue(sample, InstantValueHolder::class.java)

        Assertions.assertEquals(Instant.ofEpochSecond(sampleInstant.epochSecond), deserializedValue.createdAt)
        Assertions.assertEquals(
            Instant.ofEpochSecond(sampleInstant.epochSecond, sampleInstant.micros() * 1000),
            deserializedValue.timestamp
        )
    }


}
