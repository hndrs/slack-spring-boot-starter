package com.kreait.slack.api.contract.jackson.common.messaging.composition

import com.kreait.slack.api.contract.jackson.common.messaging.composition.Option
import com.kreait.slack.api.contract.jackson.common.messaging.composition.Text
import com.kreait.slack.api.contract.jackson.common.messaging.composition.plainTextSample


fun Option.Companion.sample(): Option = Option(Text.plainTextSample(), "value")
