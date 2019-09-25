package com.kreait.slack.broker.exception

import com.kreait.slack.api.contract.jackson.group.dialog.DialogValidationError

/**
 * Describes validation-errors in a slack dialog
 *
 * @property errors a list of containing errors
 */
class DialogValidationException(val errors: List<DialogValidationError>) : RuntimeException()
