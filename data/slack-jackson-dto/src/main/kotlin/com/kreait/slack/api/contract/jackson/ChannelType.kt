package com.kreait.slack.api.contract.jackson

/**
 * determines the type of a channel
 * can be [ChannelType.PUBLIC] [ChannelType.PRIVATE],[ChannelType.MULTIPARTY_IM] or [ChannelType.IM]
 */
enum class ChannelType(val value: String) {
    PUBLIC("public_channel"),
    PRIVATE("private_channel"),
    MULTIPARTY_IM("mpim"),
    IM("im")

}
