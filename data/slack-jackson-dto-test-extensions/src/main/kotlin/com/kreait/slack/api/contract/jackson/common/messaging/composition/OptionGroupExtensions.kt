package com.kreait.slack.api.contract.jackson.common.messaging.composition


fun OptionGroup.Companion.sample(): OptionGroup = OptionGroup(Text.plainTextSample(), listOf(Option.sample()))
