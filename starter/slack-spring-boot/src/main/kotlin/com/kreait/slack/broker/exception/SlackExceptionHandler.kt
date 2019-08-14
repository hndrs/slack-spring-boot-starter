package com.kreait.slack.broker.exception

import com.fasterxml.jackson.databind.JsonMappingException
import com.kreait.slack.api.contract.jackson.group.dialog.DialogErrorResponse
import com.kreait.slack.broker.broker.CommandBroker
import com.kreait.slack.broker.broker.EventBroker
import com.kreait.slack.broker.broker.InteractiveComponentBroker
import com.kreait.slack.broker.security.VerificationException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice(assignableTypes = [CommandBroker::class, InteractiveComponentBroker::class, EventBroker::class])
class SlackExceptionHandler(private val errorResponse: String) {

    companion object {
        private val LOG = LoggerFactory.getLogger(SlackExceptionHandler::class.java)
    }

    /**
     * Handle [DialogValidationException]s
     * This will handle dialog related exception and automatically map to the correct dialog response as mentioned here
     * https://api.slack.com/dialogs#response
     */
    @ExceptionHandler(DialogValidationException::class)
    fun handleDialogValidationException(ex: DialogValidationException): ResponseEntity<Any> {
        return ResponseEntity.ok(DialogErrorResponse(ex.errors))
    }

    /**
     * Handles [IllegalArgumentException]s
     * unwraps [IllegalArgumentException]s to check for wrapped [JsonMappingException]s (because Jackson wraps them)
     * This will also handle wrapped [DialogValidationException]s and return the correct type
     */
    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<Any> {
        val cause = ex.cause
        // throw rootcause if available
        if (cause is JsonMappingException) {
            val exception = (cause.cause as? DialogValidationException)
            if (exception != null) {
                return handleDialogValidationException(exception)
            }
            throw cause
        }
        return handleExceptionInternal(ex)
    }

    /**
     * Handle [com.kreait.slack.broker.security.VerificationException]s
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
        return ResponseEntity.ok(errorResponse)
    }
}
