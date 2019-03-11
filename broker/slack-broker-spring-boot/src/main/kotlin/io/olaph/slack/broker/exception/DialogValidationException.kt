package io.olaph.slack.broker.exception

import io.olaph.slack.dto.jackson.group.dialog.DialogValidationError

class DialogValidationException(val errors: List<DialogValidationError>) : RuntimeException()
