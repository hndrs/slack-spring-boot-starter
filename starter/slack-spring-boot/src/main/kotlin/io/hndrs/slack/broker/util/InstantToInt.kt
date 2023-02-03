package io.hndrs.slack.broker.util

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.time.Instant

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.VALUE_PARAMETER)
@JacksonAnnotationsInside
@JsonSerialize(using = InstantToUnixTimestampIntSerializer::class)
@JsonDeserialize(using = UnixTimestampIntToInstantDeserializer::class)
annotation class InstantToInt

class InstantToUnixTimestampIntSerializer : JsonSerializer<Instant>() {

    override fun serialize(value: Instant, gen: JsonGenerator, serializers: SerializerProvider?) =
        gen.writeNumber(value.epochSecond)
}

class UnixTimestampIntToInstantDeserializer : JsonDeserializer<Instant>() {

    override fun deserialize(p: JsonParser, ctxt: DeserializationContext?): Instant =
        Instant.ofEpochSecond(p.longValue)
}
