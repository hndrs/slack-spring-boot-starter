package io.hndrs.slack.broker.util

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside
import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@JacksonAnnotationsInside
@JsonAutoDetect(
    fieldVisibility = JsonAutoDetect.Visibility.ANY,
    getterVisibility = JsonAutoDetect.Visibility.NONE,
    isGetterVisibility = JsonAutoDetect.Visibility.NONE
)
@JsonCreator
@JsonIgnoreProperties(ignoreUnknown = true)
internal annotation class JacksonDataClass
