package com.kreait.slack.api.contract.jackson

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside
import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@JacksonAnnotationsInside
@JsonPropertyOrder(alphabetic = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonCreator
annotation class JacksonDataClass
