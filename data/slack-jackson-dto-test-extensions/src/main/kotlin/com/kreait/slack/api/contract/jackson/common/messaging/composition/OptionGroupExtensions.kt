package com.kreait.slack.api.contract.jackson.common.messaging.composition

import com.kreait.slack.api.contract.jackson.common.messaging.composition.Option
import com.kreait.slack.api.contract.jackson.common.messaging.composition.OptionGroup
import com.kreait.slack.api.contract.jackson.common.messaging.composition.Text
import com.kreait.slack.api.contract.jackson.common.messaging.composition.plainTextSample
import com.kreait.slack.api.contract.jackson.common.messaging.composition.sample


fun OptionGroup.Companion.sample(): OptionGroup = OptionGroup(Text.plainTextSample(), listOf(Option.sample()))
