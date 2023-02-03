package io.hndrs.slack.broker.util

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.VALUE_PARAMETER)
@JacksonAnnotationsInside
@JsonSerialize(using = JsonConverters.InstantToUnixTimestampStringSerializer::class)
@JsonDeserialize(using = JsonConverters.UnixTimestampStringToInstantDeserializer::class)
annotation class InstantToString
