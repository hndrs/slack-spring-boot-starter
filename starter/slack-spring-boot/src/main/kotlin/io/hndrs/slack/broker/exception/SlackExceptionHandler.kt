package io.hndrs.slack.broker.exception

import com.fasterxml.jackson.databind.JsonMappingException
import io.hndrs.slack.broker.command.CommandBroker
import io.hndrs.slack.broker.event.http.EventBroker
import io.hndrs.slack.broker.security.VerificationException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

/**
 * Handles
 *
 * @property errorResponse
 */
@ControllerAdvice(assignableTypes = [CommandBroker::class, EventBroker::class])
class SlackExceptionHandler {

    /**
     * Handles [IllegalArgumentException]s
     * unwraps [IllegalArgumentException]s to check for wrapped [JsonMappingException]s (because Jackson wraps them)
     * This will also handle wrapped [DialogValidationException]s and return the correct type
     */
    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<Any> {
        return handleExceptionInternal(ex)
    }

    /**
     * Handle [io.hndrs.slack.broker.security.VerificationException]s
     * This will handle signature verification related exception
     * https://api.slack.com/docs/verifying-requests-from-slack
     */
    @ExceptionHandler(VerificationException::class)
    fun handleVerificationException(ex: VerificationException): ResponseEntity<Any> {
        when {
            LOG.isDebugEnabled -> LOG.debug("Unsuccessful verification attempt", ex)
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.message)
    }

    /**
     * This will handle any other unmapped exception thrown by during invocation of any of the brokers
     */
    @ExceptionHandler(Exception::class)
    fun handleExceptionInternal(ex: Exception): ResponseEntity<Any> {
        LOG.error("Unhandled Exception:", ex)
        return ResponseEntity.ok(INTERNAL_ERROR_RESPONSE)
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(SlackExceptionHandler::class.java)
        const val INTERNAL_ERROR_RESPONSE = "Something went wrong please try again later"
    }
}
