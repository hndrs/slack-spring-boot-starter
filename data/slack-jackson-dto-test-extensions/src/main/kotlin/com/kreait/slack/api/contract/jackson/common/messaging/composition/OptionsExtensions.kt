package com.kreait.slack.api.contract.jackson.common.messaging.composition


fun Option.Companion.sample(): Option = Option(Text.plainTextSample(), "value")
