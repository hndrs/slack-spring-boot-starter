package com.kreait.slack.api.contract.jackson.common.messaging.composition

import com.kreait.slack.api.contract.jackson.common.messaging.composition.Confirmation
import com.kreait.slack.api.contract.jackson.common.messaging.composition.Text
import com.kreait.slack.api.contract.jackson.common.messaging.composition.plainTextSample
import com.kreait.slack.api.contract.jackson.common.messaging.composition.sample


fun Confirmation.Companion.sample() : Confirmation = Confirmation(Text.plainTextSample(), Text.sample(), Text.plainTextSample(), Text.plainTextSample())
