package io.olaph.slack.client

import kotlin.reflect.KClass

class ErrorResponseException(kClass: KClass<*>, errorCode: String)
    : RuntimeException("${kClass.simpleName} received error-code $errorCode")
