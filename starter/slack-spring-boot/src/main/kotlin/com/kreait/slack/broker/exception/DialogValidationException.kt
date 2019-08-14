package com.kreait.slack.broker.exception

import com.kreait.slack.api.contract.jackson.group.dialog.DialogValidationError

class DialogValidationException(val errors: List<DialogValidationError>) : RuntimeException()