package io.hndrs.slack.api.contract.jackson.common.messaging.composition


fun Confirmation.Companion.sample(): Confirmation =
    Confirmation(Text.plainTextSample(), Text.sample(), Text.plainTextSample(), Text.plainTextSample())
