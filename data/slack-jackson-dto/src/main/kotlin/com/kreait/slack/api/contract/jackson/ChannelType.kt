package com.kreait.slack.api.contract.jackson

enum class ChannelType(val value: String) {
    PUBLIC("public_channel"),
    PRIVATE("private_channel"),
    MULTIPARTY_IM("mpim"),
    IM("im")

}
