package io.olaph.slack.dto.jackson

enum class ChannelType(val value: String) {
    PUBLIC("public_channel"),
    PRIVATE("private_channel"),
    MULTIPARTY_IM("mpim"),
    IM("im")

}
