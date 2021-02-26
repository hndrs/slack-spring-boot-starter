package io.hndrs.slack.api.contract.jackson.common.messaging.composition


fun Text.Companion.sample(): Text = Text(Text.Type.MARKDOWN, "")
fun Text.Companion.plainTextSample(): Text = Text(Text.Type.PLAIN_TEXT, "")
