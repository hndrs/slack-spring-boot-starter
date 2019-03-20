package io.olaph.slack.dto.jackson.common.messaging.composition


fun OptionGroup.Companion.sample(): OptionGroup = OptionGroup(Text.plainTextSample(), listOf(Option.sample()))
