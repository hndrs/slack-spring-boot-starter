package io.olaph.slack.broker.exception

import io.olaph.slack.broker.broker.CommandBroker
import io.olaph.slack.broker.broker.EventBroker
import io.olaph.slack.broker.broker.InteractiveComponentBroker
import io.olaph.slack.broker.security.VerificationException
import io.olaph.slack.dto.jackson.group.dialog.DialogErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest


@ControllerAdvice(assignableTypes = [CommandBroker::class, InteractiveComponentBroker::class, EventBroker::class])
class SlackExceptionHandler(private val errorResponse: String) {

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
     * Handle [io.olaph.slack.broker.security.VerificationException]s
     * This will handle signature verification related exception
     * https://api.slack.com/docs/verifying-requests-from-slack
     */
    @ExceptionHandler(VerificationException::class)
    fun handleVerificationException(ex: VerificationException): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.message)
    }


    /**
     * This will handle any other unmapped exception thrown by during invocation of any of the brokers
     */
    @ExceptionHandler(Exception::class)
    fun handleExceptionInternal(ex: Exception, request: WebRequest): ResponseEntity<Any> {
        return ResponseEntity.ok(errorResponse)
    }
}
