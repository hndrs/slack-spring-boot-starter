package io.olaph.slack.dto.jackson.common.messaging.composition


fun Text.Companion.sample(): Text = Text(Text.Type.MARKDOWN, "")
fun Text.Companion.plainTextSample(): Text = Text(Text.Type.PLAIN_TEXT, "")
