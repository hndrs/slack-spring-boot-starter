package com.kreait.slack.api.contract.jackson.common.messaging.composition

import com.kreait.slack.api.contract.jackson.common.messaging.composition.Text


fun Text.Companion.sample(): Text = Text(Text.Type.MARKDOWN, "")
fun Text.Companion.plainTextSample(): Text = Text(Text.Type.PLAIN_TEXT, "")
