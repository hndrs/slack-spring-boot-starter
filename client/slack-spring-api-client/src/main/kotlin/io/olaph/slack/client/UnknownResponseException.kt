package io.olaph.slack.client

import org.springframework.http.ResponseEntity
import kotlin.reflect.KClass

class UnknownResponseException(kClass: KClass<*>, response: ResponseEntity<*>)
    : RuntimeException("${kClass.simpleName} received an unknown response $response")
