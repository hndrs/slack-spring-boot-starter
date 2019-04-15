package io.olaph.slack.client

import kotlin.reflect.KClass

class ErrorResponseException(kClass: KClass<*>, errorCode: String, errorMessage: String)
    : RuntimeException("${kClass.simpleName} received error-code $errorCode with message: $errorMessage")
